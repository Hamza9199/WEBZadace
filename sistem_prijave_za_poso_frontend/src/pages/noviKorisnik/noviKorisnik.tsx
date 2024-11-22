import { useEffect, useState } from "react";
import Footer from "../../components/footer/Footer";
import { Header } from "../../components/header/Header";
import { useNavigate, useParams } from "react-router-dom";
import './noviKorisnik.css'

const NoviKorisnik = () => {
    const [email, setEmail] = useState("");
    const [sifra, setSifra] = useState("");
    const [isAdmin, setIsAdmin] = useState(false);
    const [loading, setLoading] = useState(true);
    const { id } = useParams<{ id: string }>();
    const numericId = id ? parseInt(id, 10) : null;
    const navigate = useNavigate();

    const userId = localStorage.getItem('userId');



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


    useEffect(() => {
        if (numericId) {
            const getKorisnik = async () => {
                const response = await fetch(`http://localhost:8080/api/korisnik/${numericId}`, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                });

                if (response.ok) {
                    const data = await response.json();
                    setEmail(data.email);
                    setSifra(data.password);
                } else {
                    console.error('Failed to fetch user data');
                }
            };
            getKorisnik();
        }
    }, [numericId]);

    

    const [errors, setErrors] = useState({
        email: "",
        password: ""
    });

    interface KorisnikData {
        email: string;
        password: string;
    }

      
    if (loading) {
        return <p>Loading...</p>; 
    }


    const saveKorisnik = async (e: React.MouseEvent<HTMLButtonElement, MouseEvent>): Promise<void> => {
        e.preventDefault();

        const data: KorisnikData = {
            email: email,
            password: sifra
        };

        if (validate()) {
            const response = await fetch('http://localhost:8080/api/korisnik', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data),
            });

            if (!response.ok) {
                console.error('Neuspješno dodavanje korisnika');
                return;
            }

            const data2 = await response.json();
            console.log(data2);
            navigate('/dashboard');
        }
    };

    const updateKorisnik = async (e: React.MouseEvent<HTMLButtonElement, MouseEvent>): Promise<void> => {
        e.preventDefault();

        const data: KorisnikData = {
            email: email,
            password: sifra
        };

        if (validate()) {
            const response = await fetch(`http://localhost:8080/api/korisnik/${numericId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data),
            });

            if (!response.ok) {
                console.error('Neuspešno izmjena korisnika');
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

        if (email === "") {
            errorsCopy.email = "Email je obavezan";
            valid = false;
        } else if (!email.includes("@")) {
            errorsCopy.email = "Email mora sadrzati @";
            valid = false;
        } else if (!email.includes(".")) {
            errorsCopy.email = "Email mora sadrzati tacku";
            valid = false;
        } else if (email.length < 5) {
            errorsCopy.email = "Email mora sadrzati vise od 5 karaktera";
            valid = false;
        } else if (email.length > 50) {
            errorsCopy.email = "Email mora sadrzati manje od 50 karaktera";
            valid = false;
        } else if (email.includes(" ")) {
            errorsCopy.email = "Email ne sme sadrzati razmak";
            valid = false;
        } else if (email.includes("admin")) {
            errorsCopy.email = "Email ne sme sadrzati admin";
            valid = false;
        } else {
            errorsCopy.email = "";
        }

        if (sifra === "") {
            errorsCopy.password = "Sifra je obavezna";
            valid = false;
        } else if (sifra.length < 5) {
            errorsCopy.password = "Sifra mora sadrzati vise od 5 karaktera";
            valid = false;
        } else if (sifra.length > 50) {
            errorsCopy.password = "Sifra mora sadrzati manje od 50 karaktera";
            valid = false;
        } else if (sifra.includes(" ")) {
            errorsCopy.password = "Sifra ne sme sadrzati razmak";
            valid = false;
        } else if (sifra.includes("admin")) {
            errorsCopy.password = "Sifra ne sme sadrzati admin";
            valid = false;
        } else {
            errorsCopy.password = "";
        }

        setErrors(errorsCopy);
        return valid;
    };

    const pageTitle = () => {
        if (id) {
            return <h2 className="text-center">Izmjeni korisnika</h2>;
        } else {
            return <h2 className="text-center">Dodaj korisnika</h2>;
        }
    };

    const handleVrati = () => {
        navigate(-1);
    }

    return (
        <>
            <Header />
            <div className="dude">
            <button onClick={handleVrati}>Vrati se Nazad</button>
            {isAdmin && (
            <div>

                <div className="container">

                    <div className="row">
                        <div className="col-md-6 offset-md-3">
                            {pageTitle()}
                            <form>
                                <div className="mb-3">
                                    <label htmlFor="email" className="form-label">Email</label>
                                    <input type="email" className={"form-control" + (errors.email ? " is-invalid" : "")} id="email" value={email} onChange={(e) => setEmail(e.target.value)} />
                                    {errors.email && <div className="invalid-feedback">{errors.email}</div>}
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="sifra" className="form-label">Sifra</label>
                                    <input type="password" className={"form-control" + (errors.password ? " is-invalid" : "")} id="sifra" value={sifra} onChange={(e) => setSifra(e.target.value)} />
                                    {errors.password && <div className="invalid-feedback">{errors.password}</div>}
                                </div>
                                <button type="submit" className="btn btn-primary" onClick={numericId ? updateKorisnik : saveKorisnik}>{id ? "Izmjeni" : "Dodaj"}</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            )}
            </div>
            <Footer />
        </>
    );
};

export default NoviKorisnik;