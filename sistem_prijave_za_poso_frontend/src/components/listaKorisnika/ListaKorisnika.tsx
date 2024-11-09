import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom";


const ListaKorisnika = () => {

    interface Korisnik {
        id: number;
        email: string;
        password: string;
    }

    const [korisnici, setKorisnici] = useState<Korisnik[]>([]);

    const navigate = useNavigate();

    useEffect(() => {
        const fetchData = async () => {
            const response = await fetch("http://localhost:8080/api/korisnici");
            const data = await response.json();
            setKorisnici(data);
        }
        fetchData();
    }, []);
    

    const dodajKorisnika = async () => {
        navigate("/dodajKorisnika");
    }


    const updateKorisnika = async (id: number) => {
        navigate(`/updateKorisnika/${id}`);
    }


    const obrisiKorisnika = async (id: number) => {
        const response = await fetch(`http://localhost:8080/api/korisnici/${id}`, {
            method: "DELETE"
        });
        if (response.ok) {
            const noviKorisnici = korisnici.filter(korisnik => korisnik.id !== id);
            setKorisnici(noviKorisnici);
        }
    }
    
    return (
        <div className="container ">
            <h2 className="text-center">Lista korisnika</h2>
            <br/>

            <button className="btn btn-primary" onClick={dodajKorisnika}>Dodaj korisnika</button>

            <table className="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>email</th>
                        <th>sifra</th>
                    </tr>
                </thead>
                <tbody>
                    {korisnici.map((korisnik) => {
                        return (
                            <tr key={korisnik.id}>
                                <td>{korisnik.email}</td>
                                <td>{korisnik.password}</td>
                                <td>
                                    <button className="btn btn-info" onClick={() => updateKorisnika(korisnik.id)}>Update</button>
                                    <button className="btn btn-danger" onClick={() => obrisiKorisnika(korisnik.id)} style={{marginLeft: "10px"}}>Delete</button>
                                </td>
                            </tr>
                        )
                    })}
                </tbody>
            </table>

        
        </div>
    )
}


export default ListaKorisnika;