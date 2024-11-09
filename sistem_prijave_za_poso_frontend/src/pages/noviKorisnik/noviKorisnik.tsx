import { useEffect, useState } from "react";
import Footer from "../../components/footer/Footer";
import { Header } from "../../components/header/Header";
import { useNavigate, useParams } from "react-router-dom";



const NoviKorisnik = () => {

    const [email, setEmail] = useState("");

    const [sifra, setSifra] = useState("");

    const {id} = useParams();


    const navigate = useNavigate();


    useEffect(() => {

        if (id) {
            const getKorisnik = async () => {
                const response = await fetch(`http://localhost:8080/api/korisnik/${id}`, 
                {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
                );

                const data = await response.json();
                setEmail(data.email);
                setSifra(data.password);
            }
            getKorisnik();
            
        }
    }, [id]);


    const [errors, setErrors] = useState({
        email: "",
        password: ""
    });


    interface KorisnikData {
        email: string;
        password: string;
    }   

    const saveKorisnik = async (e: React.MouseEvent<HTMLButtonElement, MouseEvent>): Promise<void> => {
        e.preventDefault();
        

        const data: KorisnikData = {
            email: email,
            password: sifra
        }

 
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
       
    }


    const updateKorisnik = async (e: React.MouseEvent<HTMLButtonElement, MouseEvent>): Promise<void> => {
        e.preventDefault();
        

        const data: KorisnikData = {
            email: email,
            password: sifra
        }

 
        if (validate()) {
            const response = await fetch(`http://localhost:8080/api/korisnik/${id}`, {
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
       
    }




    const validate = () => {
        let valid = true;
        const errosCopy = { ...errors };
    
        if (email === "") {
            errosCopy.email = "Email je obavezan";
            valid = false;
        } else if (!email.includes("@")) {
            errosCopy.email = "Email mora sadrzati @";
            valid = false;
        } else if (!email.includes(".")) {
            errosCopy.email = "Email mora sadrzati tacku";
            valid = false;
        } else if (email.length < 5) {
            errosCopy.email = "Email mora sadrzati vise od 5 karaktera";
            valid = false;
        } else if (email.length > 50) {
            errosCopy.email = "Email mora sadrzati manje od 50 karaktera";
            valid = false;
        } else if (email.includes(" ")) {
            errosCopy.email = "Email ne sme sadrzati razmak";
            valid = false;
        } else if (email.includes("admin")) {
            errosCopy.email = "Email ne sme sadrzati admin";
            valid = false;
        } else {
            errosCopy.email = "";
        }
    
        if (sifra === "") {
            errosCopy.password = "Sifra je obavezna";
            valid = false;
        } else if (sifra.length < 5) {
            errosCopy.password = "Sifra mora sadrzati vise od 5 karaktera";
            valid = false;
        } else if (sifra.length > 50) {
            errosCopy.password = "Sifra mora sadrzati manje od 50 karaktera";
            valid = false;
        } else if (sifra.includes(" ")) {
            errosCopy.password = "Sifra ne sme sadrzati razmak";
            valid = false;
        } else if (sifra.includes("admin")) {
            errosCopy.password = "Sifra ne sme sadrzati admin";
            valid = false;
        } else {
            errosCopy.password = "";
        }
    
        setErrors(errosCopy);
        return valid;
    };


    const pageTitle = () => {
        if (id) {
            return <h2 className="text-center">Izmjeni korisnika</h2>;
        } else {
            return <h2 className="text-center">Dodaj korisnika</h2>;
        }
    }
    

    return (
        <>
            <Header/>
                <div>
                        
                    <div className="container">
                        <div className="row">
                            <div className="col-md-6 offset-md-3">
                                {
                                pageTitle()
                                }
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
                                    <button type="submit" className="btn btn-primary" onClick={id ? updateKorisnik : saveKorisnik}>{id ? "Izmeni" : "Dodaj"}</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    
                </div>
            <Footer/>
        </>
    )
}


export default NoviKorisnik;