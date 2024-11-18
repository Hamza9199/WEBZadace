import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { Header } from "../../components/header/Header";
import Footer from "../../components/footer/Footer";

interface Korisnik {
  id: number;
  email: string;
  password: string;
}

interface Job {
  id: number;
  naziv: string;
  opis: string;
}

interface ReviewDto {
  id: number;
  sadrzaj: string;
  ocjena: number;
}
    
const Profil = () => {
  const { id } = useParams<{ id: string }>();
  const history = useNavigate();

  const [korisnik, setKorisnik] = useState<Korisnik | null>(null);
  const [jobs, setJobs] = useState<Job[]>([]);
  const [reviews, setReviews] = useState<ReviewDto[]>([]);
  const [newEmail, setNewEmail] = useState<string>("");
  const [newPassword, setNewPassword] = useState<string>("");

  const apiUrlKorisnik = "http://localhost:8080/api/korisnik";
  const apiUrlJobs = "http://localhost:8080/api/jobs/kreator";
  const apiUrlReviews = "http://localhost:8080/api/reviews/korisnik";

  useEffect(() => {
    const fetchKorisnik = async () => {
      try {
        const response = await fetch(`${apiUrlKorisnik}/${id}`);
        if (!response.ok) {
          throw new Error("Greška pri učitavanju korisnika");
        }
        const data: Korisnik = await response.json();
        setKorisnik(data);
      } catch (error) {
        console.error("Greška: ", error);
      }
    };

    if (id) {
      fetchKorisnik();
    }
  }, [id]);

  useEffect(() => {
    const fetchJobs = async () => {
      try {
        const response = await fetch(`${apiUrlJobs}/${id}`, {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
        });

        if (!response.ok) {
          throw new Error("Greška pri učitavanju poslova");
        }

        const data: Job[] = await response.json();
        setJobs(data);

      } catch (error) {
        console.error("Greška: ", error);
      }
    };

    if (id) {
      fetchJobs();
    }
  }, [id]);

  useEffect(() => {
    const fetchReviews = async () => {
      try {
        const response = await fetch(`${apiUrlReviews}/${id}`);
        if (!response.ok) {
          throw new Error("Greška pri učitavanju recenzija");
        }
        const data: ReviewDto[] = await response.json();
        setReviews(data);
      } catch (error) {
        console.error("Greška: ", error);
      }
    };

    if (id) {
      fetchReviews();
    }
  }, [id]);

  const handleUpdate = async () => {
    try {
      const updatedKorisnik = {
        email: newEmail || korisnik?.email,
        password: newPassword || korisnik?.password,
      };

      const response = await fetch(`${apiUrlKorisnik}/${id}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(updatedKorisnik),
      });

      if (!response.ok) {
        throw new Error("Greška pri ažuriranju profila");
      }

      const data: Korisnik = await response.json();
      setKorisnik(data);
      history(`/profil/${id}`);
    } catch (error) {
      console.error("Greška: ", error);
    }
  };

  const obrisiPosao = async (id: number) => {
    const response = await fetch(`http://localhost:8080/api/jobs/${id}`, {
      method: "DELETE",
    });
    console.log(response);
    history(`/profil/${id}`);
  };

  const updatePosao = (id: number) => {
    history(`/updatePosao/${id}`);
  };

  const ucitajPosao = (id: number) => {
    history(`/posao/${id}`);
  };

  const handleReview = (id: number) => {
    history(`/review/${id}`)
  }
  
  const updateReview = (id: number) => {
    history(`/updateReview/${id}`);
  };

  const deleteReview = async (id: number) => {
    const response = await fetch(`http://localhost:8080/api/reviews/${id}`, {
      method: "DELETE",
    });
    if (response.ok) {
      const newReviews = reviews.filter((review) => review.id !== id);
      setReviews(newReviews);
    }
  };

  return (
    <>
      <Header />
      <div className="profile-page">
        {korisnik ? (
          <div>
            <h2>Profil Korisnika</h2>
            <p><strong>Email:</strong> {korisnik.email}</p>
            <div>
              <input
                type="email"
                placeholder="Novi email"
                value={newEmail}
                onChange={(e) => setNewEmail(e.target.value)}
              />
            </div>
            <div>
              <input
                type="password"
                placeholder="Nova lozinka"
                value={newPassword}
                onChange={(e) => setNewPassword(e.target.value)}
              />
            </div>
            <button onClick={handleUpdate}>Ažuriraj Profil</button>
          </div>
        ) : (
          <p>Učitavanje...</p>
        )}

        <div>
          <h2>Lista Poslova</h2>
          {jobs.length > 0 ? (
            <ul>
              {jobs.map((job) => (
                <li key={job.id} onDoubleClick={() => ucitajPosao(job.id)}>
                  <h3>{job.naziv}</h3>
                  <p>{job.opis}</p>
                  <button className="btn btn-info" onClick={() => updatePosao(job.id)}>
                    Update
                  </button>
                  <button
                    className="btn btn-danger"
                    onClick={() => obrisiPosao(job.id)}
                    style={{ marginLeft: "10px" }}
                  >
                    Delete
                  </button>
                </li>
              ))}
            </ul>
          ) : (
            <p>Nema poslova za ovog korisnika.</p>
          )}
        </div>

        <div>
          <h2>Recenzije</h2>
          {reviews.length > 0 ? (
            <ul>
              {reviews.map((review) => (
                <li key={review.id} onDoubleClick={() => handleReview(review.id)}>
                  <h3>{review.sadrzaj}</h3>
                  <p><strong>Ocjena:</strong> {review.ocjena}</p>
                  <button
                    className="btn btn-info"
                    onClick={() => updateReview(review.id)}
                  >
                    Update
                  </button>
                  <button
                    className="btn btn-danger"
                    onClick={() => deleteReview(review.id)}
                    style={{ marginLeft: "10px" }}
                  >
                    Delete
                  </button>
                </li>
              ))}
            </ul>
          ) : (
            <p>Korisnik nema recenzija.</p>
          )}
        </div>
      </div>
      <Footer />
    </>
  );
};

export default Profil;
