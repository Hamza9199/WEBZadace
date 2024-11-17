import { useState } from "react";
import Footer from "../../components/footer/Footer";
import { Header } from "../../components/header/Header";
import { useNavigate } from "react-router-dom";

const NovaKategorija = () => {
    const [nazivK, setNaziv] = useState("");
    const [errors, setErrors] = useState({
        nazivK: "",
    });

    const navigate = useNavigate();

    const saveKategorija = async (e: React.FormEvent<HTMLFormElement>): Promise<void> => {
        e.preventDefault();
        if (validate()) {
            const data = { nazivK: nazivK };

            try {
                const response = await fetch('http://localhost:8080/api/kategorija', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(data),
                });

                if (!response.ok) {
                    const errorMessage = await response.text();
                    console.error('Neuspješno dodavanje kategorije:', errorMessage);
                    alert('Greška pri dodavanju kategorije: ' + errorMessage);
                    return;
                }

                const data2 = await response.json();
                console.log(data2);
                navigate('/dashboard');
            } catch (error) {
                console.error('Greška u komunikaciji sa serverom:', error);
            }
        }
    };

    const validate = () => {
        let valid = true;
        const errorsCopy = { ...errors };

        if (nazivK === "") {
            errorsCopy.nazivK = "Naziv je obavezan";
            valid = false;
        } else if (nazivK.length < 3) {
            errorsCopy.nazivK = "Naziv mora biti duži od 3 karaktera";
            valid = false;
        } else if (nazivK.length > 50) {
            errorsCopy.nazivK = "Naziv ne može biti duži od 50 karaktera";
            valid = false;
        } else {
            errorsCopy.nazivK = "";
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
                            <form onSubmit={saveKategorija}> 
                                <div className="mb-3">
                                    <label htmlFor="nazivK" className="form-label">Naziv kategorije</label>
                                    <input
                                        type="text"
                                        className={"form-control" + (errors.nazivK ? " is-invalid" : "")}
                                        id="nazivK"
                                        value={nazivK}
                                        onChange={(e) => setNaziv(e.target.value)}
                                    />
                                    {errors.nazivK && <div className="invalid-feedback">{errors.nazivK}</div>}
                                </div>

                                <button type="submit" className="btn btn-primary">Dodaj</button>
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
