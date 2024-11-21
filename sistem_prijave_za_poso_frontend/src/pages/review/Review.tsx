import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import "./Review.css";
import { Header } from "../../components/header/Header";
import Footer from "../../components/footer/Footer";

export const Review = () => {
  const [review, setReview] = useState<{ sadrzaj: string; ocjena: number } | null>(null);
  const { id } = useParams<{ id: string }>(); 
  const navigate = useNavigate();

  useEffect(() => {
    const fetchReview = async () => {
      try {
        const response = await fetch(`http://localhost:8080/api/reviews/${id}`);
        if (response.ok) {
          const data = await response.json();
          setReview(data); 
        } else {
          console.error("Neuspješno učitavanje recenzije");
        }
      } catch (error) {
        console.error("Greška prilikom učitavanja recenzije:", error);
      }
    };

    fetchReview();
  }, [id]); 

  const handleVrati = () => {
    navigate(-1);
  }

  return (
    <>
      <Header />
      <button onClick={handleVrati}>Vrati se Nazad</button>

      <div className="review-container">
        {review ? (
          <>
            <h2>Recenzija</h2>
            <div className="review-content">
              <p><strong>Sadržaj:</strong> {review.sadrzaj}</p>
              <p><strong>Ocjena:</strong> {review.ocjena}</p>
            </div>
          </>
        ) : (
          <p>Učitavanje recenzije...</p>
        )}
      </div>
      <Footer />
    </>
  );
};
