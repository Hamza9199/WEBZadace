import Footer from '../../components/footer/Footer';
import { Header } from '../../components/header/Header';
import ListaKorisnika from '../../components/listaKorisnika/ListaKorisnika';
import ListaPoslova from '../../components/listaPoslova/ListaPoslova'; 
import DashboardChart from '../../components/dashboardChart/DashboardChart'; 
import './Dashbord.css';
import ListaReviews from '../../components/listaReviews/ListaReviews';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Dashbord = () => {
    const [isAdmin, setIsAdmin] = useState(false);
    const [loading, setLoading] = useState(true);
    const userId = localStorage.getItem('userId');
    const navigate = useNavigate();

    useEffect(() => {
        const fetchIsAdmin = async () => {
            try {
                const response = await fetch(`http://localhost:8080/api/korisnik/${userId}/is-admin`);
                if (response.ok) {
                    const data = await response.json();
                    setIsAdmin(data);
                    if (!data) {
                        navigate('/'); 
                    }
                } else {
                    console.error('Greska');
                    navigate('/'); 
                }
            } catch (error) {
                console.error('Error:', error);
                navigate('/'); 
            } finally {
                setLoading(false); 
            }
        };

        if (userId) {
            fetchIsAdmin();
        } else {
            navigate('/'); 
        }
    }, [userId, navigate]);

    if (loading) {
        return <p>Loading...</p>; 
    }

    return (
        <>
            <Header />
            {isAdmin && (
                <div className="dashboard-container">
                    <h2 className="text-center">Lista Korisnika</h2>
                    <div className="dashboard-section">
                        <ListaKorisnika /> 
                    </div>
                    <h2 className="text-center">Lista Poslova</h2>
                    <div className="dashboard-section">
                        <ListaPoslova searchTerm={''} selectedCategory={''} /> 
                    </div>
                    <h2 className="text-center">Lista Recenzicja</h2>
                    <div className="dashboard-section">
                        <ListaReviews /> 
                    </div>
                    <div className="dashboard-section">
                        <DashboardChart />
                    </div>
                </div>
            )}
            <Footer />
        </>
    );
};

export { Dashbord };
