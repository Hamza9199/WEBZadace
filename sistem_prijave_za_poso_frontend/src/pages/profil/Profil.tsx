import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { Header } from "../../components/header/Header";
import Footer from "../../components/footer/Footer";

interface Korisnik {
  id: number;
  email: string;
  password: string;
}

const Profil = () => {
  const { id } = useParams<{ id: string }>();
  const history = useNavigate();
  
  const [korisnik, setKorisnik] = useState<Korisnik | null>(null);
  const [newEmail, setNewEmail] = useState<string>("");
  const [newPassword, setNewPassword] = useState<string>("");

  const apiUrl = "http://localhost:8080/api/korisnik"; 

  useEffect(() => {
    const fetchKorisnik = async () => {
      try {
        const response = await fetch(`${apiUrl}/${id}`);
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

  const handleUpdate = async () => {
    try {
      const updatedKorisnik = {
        email: newEmail || korisnik?.email,
        password: newPassword || korisnik?.password,
      };

      const response = await fetch(`${apiUrl}/${id}`, {
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

  return (
    <>
      <Header/>
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
      </div>
      <Footer/>
    </>
  );
};

export default Profil;
