import { useState, useEffect } from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import "./NoviReview.css";
import { Header } from "../../components/header/Header";
import Footer from "../../components/footer/Footer";

export const NoviReview = () => {
  const [sadrzaj, setSadrzaj] = useState(""); 
  const [ocjena, setOcjena] = useState<number | "">("");  
  const navigate = useNavigate();
  const { id } = useParams<{ id: string }>(); 
  const isEditMode = Boolean(id); 
  const location = useLocation();
  const posaoid = location.state?.posaoid || null; 
  const userId = localStorage.getItem("userId");
  const userIdInt: number = userId ? parseInt(userId, 10) : 0; 
  const posaoIdInt: number = posaoid ? parseInt(posaoid, 10) : 0;

  useEffect(() => {
    if (isEditMode) {
      const fetchReview = async () => {
        try {
          const response = await fetch(`http://localhost:8080/api/reviews/${id}`);
          if (response.ok) {
            const data = await response.json();
            setSadrzaj(data.sadrzaj);
            setOcjena(data.ocjena);
          } else {
            console.error("Neuspješno učitavanje recenzije");
          }
        } catch (error) {
          console.error("Greška prilikom učitavanja recenzije:", error);
        }
      };
      fetchReview();
    }
  }, [id, isEditMode]);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!sadrzaj || !ocjena) {
      alert("Sva polja su obavezna!");
      return;
    }

    

    const review = { sadrzaj, ocjena, korisnikId: userIdInt, posaoId: posaoIdInt };
    try {
      const response = await fetch(
        isEditMode ? `http://localhost:8080/api/reviews/${id}` : "http://localhost:8080/api/reviews",
        {
          method: isEditMode ? "PUT" : "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(review),
        }
      );

      if (response.ok) {
        const responseData = await response.json();
        console.log(responseData)
        alert(isEditMode ? "Recenzija uspješno ažurirana!" : "Recenzija uspješno kreirana!");
        navigate("/");  
      } else {
        alert("Greška pri " + (isEditMode ? "ažuriranju" : "kreiranju") + " recenzije.");
      }
    } catch (error) {
      console.error("Fetch error: ", error);
      alert("Došlo je do greške.");
    }
  };

  const handleVrati = () => {
    navigate(-1);
}

  return (
    <>
      <Header />
      <button onClick={handleVrati}>Vrati se Nazad</button>

      <div className="novi-review-container">
        <h2>{isEditMode ? "Ažuriraj Recenziju" : "Dodaj Recenziju"}</h2>
        <form onSubmit={handleSubmit} className="novi-review-form">
          <div className="form-group">
            <label htmlFor="sadrzaj">Sadržaj recenzije:</label>
            <textarea
              id="sadrzaj"
              value={sadrzaj}
              onChange={(e) => setSadrzaj(e.target.value)}
              required
            ></textarea>
          </div>

          <div className="form-group">
            <label htmlFor="ocjena">Ocjena (1-5):</label>
            <input
              type="number"
              id="ocjena"
              value={ocjena}
              onChange={(e) => setOcjena(Number(e.target.value))}
              min="1"
              max="5"
              required
            />
          </div>

          <button type="submit" className="btn-submit">
            {isEditMode ? "Ažuriraj Recenziju" : "Pošaljite Recenziju"}
          </button>
        </form>
      </div>
      <Footer />
    </>
  );
};
