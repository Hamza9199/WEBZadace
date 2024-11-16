import { useNavigate } from "react-router-dom";
import { Header } from "../../components/header/Header";
import Footer from "../../components/footer/Footer";



export const Home = () => {

  const navigate = useNavigate();

  const handleLogout = async () => {
    localStorage.removeItem('isAuthenticated');

      const response = await fetch('http://localhost:8080/api/logout', {
          method: 'POST',
          headers: {
              'Content-Type': 'application/json',
          },
      });

      console.log(response.status)

    navigate('/login');
  };

  return (
    <>
      <Header />

      <button onClick={handleLogout}>Logout</button>


      <Footer />
    </>
  );
};

