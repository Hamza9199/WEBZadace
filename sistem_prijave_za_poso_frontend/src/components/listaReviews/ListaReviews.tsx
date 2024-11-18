import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Footer from "../../components/footer/Footer";
import { Header } from "../../components/header/Header";

interface Review {
  id: number;
  sadrzaj: string;
  ocjena: number;
  korisnikId: number;
  posaoId: number;
}

const ListaReviews: React.FC = () => {
  const [reviews, setReviews] = useState<Review[]>([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchReviews = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/reviews");
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        const data: Review[] = await response.json();
        setReviews(data);
      } catch (error) {
        console.error("Fetch error: ", error);
      }
    };
    fetchReviews();
  }, []);

  const updateReview = (id: number) => {
    navigate(`/updateReview/${id}`);
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

  const handleReview = (id: number) => {
    navigate(`/review/${id}`)
  }

  return (
    <>
      <Header />
      <div className="container">
        <table className="table table-striped table-bordered">
          <thead>
            <tr>
              <th>ID</th>
              <th>Sadržaj</th>
              <th>Ocjena</th>
              <th>Korisnik</th>
              <th>Posao</th>
              <th>Akcije</th>
            </tr>
          </thead>
          <tbody>
            {reviews.map((review) => (
              <tr key={review.id} onDoubleClick={() => handleReview(review.id)}>
                <td>{review.id}</td>
                <td>{review.sadrzaj}</td>
                <td>{review.ocjena}</td>
                <td>{review.korisnikId}</td>
                <td>{review.posaoId}</td>

                <td>
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
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <Footer />
    </>
  );
};

export default ListaReviews;
