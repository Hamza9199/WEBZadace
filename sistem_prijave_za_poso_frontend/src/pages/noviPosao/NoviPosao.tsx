import { useEffect, useState } from "react";
import Footer from "../../components/footer/Footer";
import { Header } from "../../components/header/Header";
import { useNavigate, useParams } from "react-router-dom";

const NoviPosao = () => {
    const [naziv, setNaziv] = useState("");
    const [opis, setOpis] = useState("");
    const [kategorija, setKategorija] = useState(""); 
    const [rating, setRating] = useState("");
    const kategorije = ["IT", "Marketing", "Finansije", "Menadžment", "Prodaja"]; 

    const { id } = useParams<{ id: string }>();
    const numericId = id ? parseInt(id, 10) : null;
    const navigate = useNavigate();

    useEffect(() => {
        if (numericId) {
            const getPosao = async () => {
                try {
                    const response = await fetch(`http://localhost:8080/api/jobs/${numericId}`);
                    if (response.ok) {
                        const data = await response.json();
                        setNaziv(data.naziv);
                        setOpis(data.opis);
                        setRating(data.rating)
                        setKategorija(data.kategorija); 
                        
                    } else {
                        console.error("Failed to fetch job data");
                    }
                } catch (error) {
                    console.error("Error fetching job:", error);
                }
            };
            getPosao();
        }
    }, [numericId]);

    const [errors, setErrors] = useState({
        naziv: "",
        opis: "",
        kategorija: "",
    });

    const validate = (): boolean => {
        let valid = true;
        const errorsCopy = { ...errors };

        if (naziv === "") {
            errorsCopy.naziv = "Naziv je obavezan";
            valid = false;
        } else if (naziv.length < 3) {
            errorsCopy.naziv = "Naziv mora imati više od 3 karaktera";
            valid = false;
        }

        if (opis === "") {
            errorsCopy.opis = "Opis je obavezan";
            valid = false;
        } else if (opis.length < 5) {
            errorsCopy.opis = "Opis mora imati više od 5 karaktera";
            valid = false;
        }

        if (!kategorije.includes(kategorija)) {
            errorsCopy.kategorija = "Morate odabrati kategoriju iz liste";
            valid = false;
        } else {
            errorsCopy.kategorija = "";
        }

        setErrors(errorsCopy);
        return valid;
    };

    const saveOrUpdatePosao = async (e: React.MouseEvent<HTMLButtonElement>): Promise<void> => {
        e.preventDefault();

        if (!validate()) return;

        const userId = localStorage.getItem("userId");
        
        const userIdInt: number = userId ? parseInt(userId, 10) : 0; 

        const data = { naziv, opis, rating , kategorija , kreatorid: userIdInt};
        const url = numericId
            ? `http://localhost:8080/api/jobs/${numericId}`
            : "http://localhost:8080/api/jobs";

        const method = numericId ? "PUT" : "POST";

        console.log("Sending data:", data);  

        try {
            const response = await fetch(url, {
                method,
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(data),
            });

            if (response.ok) {
                
                navigate(`/profil/${userId}`);

            } else {
                console.error("Failed to save job");
            }
        } catch (error) {
            console.error("Error saving job:", error);
        }
    };

    return (
        <>
            <Header />
            <div className="container">
                <div className="row">
                    <div className="col-md-6 offset-md-3">
                        <h2 className="text-center">{id ? "Izmjeni posao" : "Dodaj posao"}</h2>
                        <form>
                            <div className="mb-3">
                                <label htmlFor="naziv" className="form-label">Naziv</label>
                                <input
                                    type="text"
                                    className={`form-control ${errors.naziv ? "is-invalid" : ""}`}
                                    id="naziv"
                                    value={naziv}
                                    onChange={(e) => setNaziv(e.target.value)}
                                />
                                {errors.naziv && <div className="invalid-feedback">{errors.naziv}</div>}
                            </div>
                            <div className="mb-3">
                                <label htmlFor="opis" className="form-label">Opis</label>
                                <textarea
                                    className={`form-control ${errors.opis ? "is-invalid" : ""}`}
                                    id="opis"
                                    value={opis}
                                    onChange={(e) => setOpis(e.target.value)}
                                />
                                {errors.opis && <div className="invalid-feedback">{errors.opis}</div>}
                            </div>
                            <div className="mb-3">
                                <label htmlFor="kategorija" className="form-label">Kategorija</label>
                                <select
                                    className={`form-control ${errors.kategorija ? "is-invalid" : ""}`}
                                    id="kategorija"
                                    value={kategorija}
                                    onChange={(e) => setKategorija(e.target.value)}
                                >
                                    <option value="">Odaberite kategoriju</option>
                                    {kategorije.map((kat) => (
                                        <option key={kat} value={kat}>
                                            {kat}
                                        </option>
                                    ))}
                                </select>
                                {errors.kategorija && (
                                    <div className="invalid-feedback">{errors.kategorija}</div>
                                )}
                            </div>
                            <button
                                type="submit"
                                className="btn btn-primary"
                                onClick={saveOrUpdatePosao}
                            >
                                {id ? "Izmjeni" : "Dodaj"}
                            </button>
                        </form>
                    </div>
                </div>
            </div>
            <Footer />
        </>
    );
};

export default NoviPosao;
