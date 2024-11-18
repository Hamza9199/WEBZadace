import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

interface Job {
  id: number;
  naziv: string;
  opis: string;
  rating: number;
  kategorija: string;
  kreatorid : number;
}

interface ListaPoslovaProps {
  searchTerm: string;
  selectedCategory: string;
}

const ListaPoslova: React.FC<ListaPoslovaProps> = ({ searchTerm, selectedCategory }) => {
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
    };
    fetchData();
  }, []);

  const dodajPosao = () => {
    navigate("/dodajPosao");
  };

  const updatePosao = (id: number) => {
    navigate(`/updatePosao/${id}`);
  };

  const obrisiPosao = async (id: number) => {
    const response = await fetch(`http://localhost:8080/api/jobs/${id}`, {
      method: "DELETE",
    });
    if (response.ok) {
      const noviPoslovi = poslovi.filter((posao) => posao.id !== id);
      setPoslovi(noviPoslovi);
    }
  };

  const ucitajPosao = (id: number) => {
    navigate(`/posao/${id}`);
  };

  const filtriraniPoslovi = poslovi.filter((posao) => {
    const matchesSearch = posao.naziv.toLowerCase().includes(searchTerm.toLowerCase());
    const matchesCategory = selectedCategory
      ? posao.kategorija === selectedCategory
      : true;
    return matchesSearch && matchesCategory;
  });

  return (
    <div className="container">
      

      <button className="btn btn-primary" onClick={dodajPosao}>
        Dodaj posao
      </button>

      <table className="table table-striped table-bordered">
        <thead>
          <tr>
            <th>id</th>
            <th>naziv</th>
            <th>opis</th>
            <th>rating</th>
            <th>kategorija</th>
            <th>kreator</th>
            <th>Akcije</th>
          </tr>
        </thead>
        <tbody>
          {filtriraniPoslovi.map((posao) => (
            <tr key={posao.id} onDoubleClick={() => ucitajPosao(posao.id)}>
              <td>{posao.id}</td>
              <td>{posao.naziv}</td>
              <td>{posao.opis}</td>
              <td>{posao.rating}</td>
              <td>{posao.kategorija}</td>
              <td>{posao.kreatorid}</td>

              <td>
                <button className="btn btn-info" onClick={() => updatePosao(posao.id)}>
                  Update
                </button>
                <button
                  className="btn btn-danger"
                  onClick={() => obrisiPosao(posao.id)}
                  style={{ marginLeft: "10px" }}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ListaPoslova;
