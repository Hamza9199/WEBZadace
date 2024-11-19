import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { Header } from "../../components/header/Header";
import Footer from "../../components/footer/Footer";

interface Job {
  id: number;
  naziv: string;
  opis: string;
  rating: number;
  kategorija: string;
}

interface Review {
  id: number;
  sadrzaj: string; 
  ocjena: number; 
  korisnikid: number;
  posaoid: number;
}

const Posao = () => {
  const { id } = useParams<{ id: string }>();
  const [job, setJob] = useState<Job | null>(null);
  const [reviews, setReviews] = useState<Review[]>([]);
  const [loadingJob, setLoadingJob] = useState<boolean>(true);
  const [loadingReviews, setLoadingReviews] = useState<boolean>(true);
  const navigate = useNavigate();
  const userId = localStorage.getItem("userId");

  const currentUserId = userId ? parseInt(userId, 10) : 0;

  useEffect(() => {
    if (id) {
      const fetchJobDetails = async () => {
        try {
          const response = await fetch(`http://localhost:8080/api/jobs/${id}`);
          if (!response.ok) {
            throw new Error("Nije moguće dohvatiti podatke o poslu.");
          }
          const data = await response.json();
          setJob(data);
        } catch (error) {
          console.error("Došlo je do greške:", error);
        } finally {
          setLoadingJob(false);
        }
      };
      fetchJobDetails();
    }
  }, [id]);

  useEffect(() => {
    if (id) {
      const fetchReviews = async () => {
        try {
          const response = await fetch(`http://localhost:8080/api/reviews/job/${id}`);
          if (!response.ok) {
            throw new Error("Nije moguće dohvatiti recenzije za posao.");
          }
          const data = await response.json();
          setReviews(data);
        } catch (error) {
          console.error("Došlo je do greške:", error);
        } finally {
          setLoadingReviews(false);
        }
      };
      fetchReviews();
    }
  }, [id]);

  const handleReview = () => {
    navigate("/noviReview", { state: { posaoid: id } }); 
  };
  
  const handleReview2 = (id: number) => {
    navigate(`/review/${id}`)
  }

  
  const handlePrijavaPosla = async () => {
    if (!id) return;

    const jobKorisnikData = {
      korisnikId: currentUserId,
      posaoId: parseInt(id),
    };

    try {
      const response = await fetch("http://localhost:8080/api/job-korisnik", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(jobKorisnikData),
      });

      if (!response.ok) {
        throw new Error("Došlo je do greške pri prijavi na posao.");
      }

      alert("Uspješno ste se prijavili na posao!");
    } catch (error) {
      console.error("Greška pri prijavi na posao:", error);
      alert("Prijava na posao nije uspjela. Pokušajte ponovo.");
    }
  };


  return (
    <>
      <Header />
      <div className="job-details">
        {loadingJob ? (
          <div>Učitavanje podataka o poslu...</div>
        ) : job ? (
          <div>
            <h2>{job.naziv}</h2>
            <p>
              <strong>Kategorija:</strong> {job.kategorija}
            </p>
            <p>
              <strong>Ocjena:</strong> {job.rating}
            </p>
            <p>
              <strong>Opis:</strong> {job.opis}
            </p>
          </div>
        ) : (
          <div>Posao nije pronađen.</div>
        )}
      </div>

      <div>
        <button onClick={handlePrijavaPosla}>
          Prijava na Posao
        </button>
      </div>

      <div>
        <button onClick={handleReview}>Ostavi Review Na Posao</button>
      </div>

      <div className="job-reviews">
        <h3>Recenzije</h3>
        {loadingReviews ? (
          <div>Učitavanje recenzija...</div>
        ) : reviews.length > 0 ? (
          <ul>
            {reviews.map((review) => (
              <li key={review.id} onDoubleClick={() => handleReview2(review.id)}>
                
                <p>
                  <strong>Komentar:</strong> {review.sadrzaj}
                </p>
                <p>
                  <strong>Ocjena:</strong> {review.ocjena}
                </p>
                
                  
              </li>
            ))}
          </ul>
        ) : (
          <p>Trenutno nema recenzija za ovaj posao.</p>
        )}
      </div>
      <Footer />
    </>
  );
};

export default Posao;
