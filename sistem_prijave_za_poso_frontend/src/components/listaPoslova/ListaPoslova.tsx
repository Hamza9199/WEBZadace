import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const ListaPoslova = () => {

    interface Job {
        id: number;
        naziv: string;
        opis: string;
        rating: number;
        kategorijaId: number;
    }

    const [poslovi, setPoslovi] = useState<Job[]>([]);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch("http://localhost:8080/api/jobs");
                if (!response.ok) {
                    throw new Error("Network response was not ok");
                }
                const data: Job[] = await response.json();
                setPoslovi(data);
            } catch (error) {
                console.error("Fetch error: ", error);
            }
        }
        fetchData();
    }, []);

    const dodajPosao = () => {
        navigate("/dodajPosao");
    }

    const updatePosao = (id: number) => {
        navigate(`/updatePosao/${id}`);
    }

    const obrisiPosao = async (id: number) => {
        const response = await fetch(`http://localhost:8080/api/jobs/${id}`, {
            method: "DELETE"
        });
        if (response.ok) {
            const noviPoslovi = poslovi.filter(posao => posao.id !== id);
            setPoslovi(noviPoslovi);
        }
    }

    return (
        <div className="container">
            <h2 className="text-center">Lista poslova</h2>
            <br />

            <button className="btn btn-primary" onClick={dodajPosao}>Dodaj posao</button>

            <table className="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>id</th>
                        <th>naziv</th>
                        <th>opis</th>
                        <th>rating</th>
                        <th>kategorija id</th>
                        <th>Akcije</th>
                    </tr>
                </thead>
                <tbody>
                    {poslovi.map((posao) => {
                        return (
                            <tr key={posao.id}>
                                <td>{posao.id}</td>
                                <td>{posao.naziv}</td>
                                <td>{posao.opis}</td>
                                <td>{posao.rating}</td>
                                <td>{posao.kategorijaId}</td>
                                <td>
                                    <button className="btn btn-info" onClick={() => updatePosao(posao.id)}>Update</button>
                                    <button className="btn btn-danger" onClick={() => obrisiPosao(posao.id)} style={{ marginLeft: "10px" }}>Delete</button>
                                </td>
                            </tr>
                        )
                    })}
                </tbody>
            </table>
        </div>
    )
}

export default ListaPoslova;
