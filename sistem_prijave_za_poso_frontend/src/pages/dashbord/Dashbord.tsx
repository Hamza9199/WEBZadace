import Footer from '../../components/footer/Footer';
import { Header } from '../../components/header/Header';
import ListaKorisnika from '../../components/listaKorisnika/ListaKorisnika';
import ListaPoslova from '../../components/listaPoslova/ListaPoslova'; 
import './Dashbord.css';

const Dashbord = () => {
    return (
        <>
            <Header />

            <div className="dashboard-container">
                <div className="dashboard-section">
                    <h2>Lista Korisnika</h2>
                    <ListaKorisnika /> 
                </div>

                <div className="dashboard-section">
                    <h2>Lista Poslova</h2>
                    <ListaPoslova /> 
                </div>
            </div>

            <Footer />
        </>
    );
}

export { Dashbord };
