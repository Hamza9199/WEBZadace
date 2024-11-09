import { useNavigate } from "react-router-dom";
import { Header } from "../../components/header/Header";
import Footer from "../../components/footer/Footer";



export const Home = () => {

  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem('isAuthenticated');

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

