import Footer from '../../components/footer/Footer';
import { Header } from '../../components/header/Header';
import ListaKorisnika from '../../components/listaKorisnika/ListaKorisnika';
import ListaPoslova from '../../components/listaPoslova/ListaPoslova'; 
import DashboardChart from '../../components/dashboardChart/DashboardChart'; 
import './Dashbord.css';
import ListaReviews from '../../components/listaReviews/ListaReviews';

const Dashbord = () => {
    return (
        <>
            <Header />

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

            <Footer />
        </>
    );
}

export { Dashbord };
