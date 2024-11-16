import { useState } from "react";
import Footer from "../../components/footer/Footer";
import { Header } from "../../components/header/Header";
import { useNavigate } from "react-router-dom";

const NovaKategorija = () => {
    const [naziv, setNaziv] = useState("");
    const [opis, setOpis] = useState("");
    const [errors, setErrors] = useState({
        naziv: "",
        opis: ""
    });

    const navigate = useNavigate();

    const saveKategorija = async (e: React.MouseEvent<HTMLButtonElement, MouseEvent>): Promise<void> => {
        e.preventDefault();

        const data = {
            naziv: naziv,
            opis: opis
        };

        if (validate()) {
            const response = await fetch('http://localhost:8080/api/kategorija', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data),
            });

            if (!response.ok) {
                console.error('Neuspješno dodavanje kategorije');
                return;
            }

            const data2 = await response.json();
            console.log(data2);
            navigate('/dashboard');  
        }
    };

    const validate = () => {
        let valid = true;
        const errorsCopy = { ...errors };

        if (naziv === "") {
            errorsCopy.naziv = "Naziv je obavezan";
            valid = false;
        } else if (naziv.length < 3) {
            errorsCopy.naziv = "Naziv mora biti duži od 3 karaktera";
            valid = false;
        } else if (naziv.length > 50) {
            errorsCopy.naziv = "Naziv ne može biti duži od 50 karaktera";
            valid = false;
        } else {
            errorsCopy.naziv = "";
        }

        if (opis === "") {
            errorsCopy.opis = "Opis je obavezan";
            valid = false;
        } else if (opis.length < 10) {
            errorsCopy.opis = "Opis mora biti duži od 10 karaktera";
            valid = false;
        } else if (opis.length > 200) {
            errorsCopy.opis = "Opis ne može biti duži od 200 karaktera";
            valid = false;
        } else {
            errorsCopy.opis = "";
        }

        setErrors(errorsCopy);
        return valid;
    };

    return (
        <>
            <Header />
            <div>
                <div className="container">
                    <div className="row">
                        <div className="col-md-6 offset-md-3">
                            <h2 className="text-center">Dodaj novu kategoriju</h2>
                            <form>
                                <div className="mb-3">
                                    <label htmlFor="naziv" className="form-label">Naziv kategorije</label>
                                    <input type="text" className={"form-control" + (errors.naziv ? " is-invalid" : "")} id="naziv" value={naziv} onChange={(e) => setNaziv(e.target.value)} />
                                    {errors.naziv && <div className="invalid-feedback">{errors.naziv}</div>}
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="opis" className="form-label">Opis kategorije</label>
                                    <textarea className={"form-control" + (errors.opis ? " is-invalid" : "")} id="opis" value={opis} onChange={(e) => setOpis(e.target.value)} />
                                    {errors.opis && <div className="invalid-feedback">{errors.opis}</div>}
                                </div>
                                <button type="submit" className="btn btn-primary" onClick={saveKategorija}>Dodaj</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <Footer />
        </>
    );
};

export default NovaKategorija;
