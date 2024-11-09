import Footer from '../../components/footer/Footer';
import { Header } from '../../components/header/Header';
import ListaKorisnika from '../../components/listaKorisnika/ListaKorisnika';
import './Dashbord.css';


const Dashbord = () => {
    return (
        <>
            <Header />

            <ListaKorisnika />

            <Footer />
        </>
    );
}

export { Dashbord };