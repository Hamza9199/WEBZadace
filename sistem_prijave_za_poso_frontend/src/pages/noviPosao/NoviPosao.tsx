import { useEffect, useState } from "react";
import Footer from "../../components/footer/Footer";
import { Header } from "../../components/header/Header";
import { useNavigate, useParams } from "react-router-dom";

const NoviPosao = () => {
    const [naziv, setNaziv] = useState("");
    const [opis, setOpis] = useState("");
    const { id } = useParams<{ id: string }>();
    const numericId = id ? parseInt(id, 10) : null;
    const navigate = useNavigate();

    useEffect(() => {
        if (numericId) {
            const getPosao = async () => {
                const response = await fetch(`http://localhost:8080/api/jobs/${numericId}`, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                });

                if (response.ok) {
                    const data = await response.json();
                    setNaziv(data.naziv);
                    setOpis(data.opis);
                } else {
                    console.error('Failed to fetch job data');
                }
            };
            getPosao();
        }
    }, [numericId]);

    const [errors, setErrors] = useState({
        naziv: "",
        opis: ""
    });

    interface PosaoData {
        naziv: string;
        opis: string;
    }

    const savePosao = async (e: React.MouseEvent<HTMLButtonElement, MouseEvent>): Promise<void> => {
        e.preventDefault();

        const data: PosaoData = {
            naziv: naziv,
            opis: opis
        };

        if (validate()) {
            const response = await fetch('http://localhost:8080/api/jobs', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data),
            });

            if (!response.ok) {
                console.error('Neuspješno dodavanje posla');
                return;
            }

            const data2 = await response.json();
            console.log(data2);
            navigate('/dashboard');
        }
    };

    const updatePosao = async (e: React.MouseEvent<HTMLButtonElement, MouseEvent>): Promise<void> => {
        e.preventDefault();

        const data: PosaoData = {
            naziv: naziv,
            opis: opis
        };

        if (validate()) {
            const response = await fetch(`http://localhost:8080/api/jobs/${numericId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data),
            });

            if (!response.ok) {
                console.error('Neuspešno izmjena posla');
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
            errorsCopy.naziv = "Naziv mora imati vise od 3 karaktera";
            valid = false;
        } else if (naziv.length > 50) {
            errorsCopy.naziv = "Naziv mora biti manji od 50 karaktera";
            valid = false;
        } else {
            errorsCopy.naziv = "";
        }

        if (opis === "") {
            errorsCopy.opis = "Opis je obavezan";
            valid = false;
        } else if (opis.length < 5) {
            errorsCopy.opis = "Opis mora imati vise od 5 karaktera";
            valid = false;
        } else if (opis.length > 200) {
            errorsCopy.opis = "Opis mora biti manji od 200 karaktera";
            valid = false;
        } else {
            errorsCopy.opis = "";
        }

        setErrors(errorsCopy);
        return valid;
    };

    const pageTitle = () => {
        if (id) {
            return <h2 className="text-center">Izmjeni posao</h2>;
        } else {
            return <h2 className="text-center">Dodaj posao</h2>;
        }
    };

    return (
        <>
            <Header />
            <div>
                <div className="container">
                    <div className="row">
                        <div className="col-md-6 offset-md-3">
                            {pageTitle()}
                            <form>
                                <div className="mb-3">
                                    <label htmlFor="naziv" className="form-label">Naziv</label>
                                    <input type="text" className={"form-control" + (errors.naziv ? " is-invalid" : "")} id="naziv" value={naziv} onChange={(e) => setNaziv(e.target.value)} />
                                    {errors.naziv && <div className="invalid-feedback">{errors.naziv}</div>}
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="opis" className="form-label">Opis</label>
                                    <textarea className={"form-control" + (errors.opis ? " is-invalid" : "")} id="opis" value={opis} onChange={(e) => setOpis(e.target.value)} />
                                    {errors.opis && <div className="invalid-feedback">{errors.opis}</div>}
                                </div>
                                <button type="submit" className="btn btn-primary" onClick={numericId ? updatePosao : savePosao}>{id ? "Izmjeni" : "Dodaj"}</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <Footer />
        </>
    );
};

export default NoviPosao;
