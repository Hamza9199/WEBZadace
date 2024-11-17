import { useEffect, useState } from "react";
import { Header } from "../../components/header/Header";
import Footer from "../../components/footer/Footer";
import "./Home.css";
import { useNavigate } from "react-router-dom";

interface Job {
  id: number;
  naziv: string;
  opis: string;
  rating: number;
  kategorija: string;
}

export const Home = () => {
  const [poslovi, setPoslovi] = useState<Job[]>([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [selectedCategory, setSelectedCategory] = useState("");
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

  const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearchTerm(e.target.value);
  };

  const handleCategoryChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setSelectedCategory(e.target.value);

  };

  const filtriraniPoslovi = poslovi.filter((posao) => {
    const matchesSearch = posao.naziv.toLowerCase().includes(searchTerm.toLowerCase());
    
    const matchesCategory = selectedCategory
      ? posao.kategorija.toLowerCase() === selectedCategory.toLowerCase()
      : true;
  
    return matchesSearch && matchesCategory;
  });
  

  const ucitajPosao = (id: number) => {
    navigate(`/posao/${id}`);
  };

  const handleDodaj = () =>{
    navigate("/dodajPosao");

  }

  return (
    <>
      <Header />

      <div>
        <label onClick={handleDodaj}>
          Želis dodati posao?
        </label>
      </div>

      <div className="home-container">
        <div className="filter-container">
          <input
            type="text"
            className="search-bar"
            placeholder="Pretraži poslove..."
            value={searchTerm}
            onChange={handleSearchChange}
          />

          <select
            className="category-filter"
            value={selectedCategory}
            onChange={handleCategoryChange}
          >
            <option value="">Sve kategorije</option>
            <option value="IT">IT</option>
            <option value="Marketing">Marketing</option>
            <option value="Finansije">Finansije</option>
            <option value="Obrazovanje">Obrazovanje</option>
          </select>

        </div>

        <div className="job-list-container">
          {filtriraniPoslovi.length > 0 ? (
            <table className="table table-striped table-bordered">
              <thead>
                <tr>
                  <th>naziv</th>
                  <th>kategorija</th>
                  <th>opis</th>
                  
                </tr>
              </thead>
              <tbody>
                {filtriraniPoslovi.map((posao) => (
                  <tr key={posao.id} onDoubleClick={() => ucitajPosao(posao.id)}>
                    <td>{posao.naziv}</td>
                    <td>{posao.kategorija}</td>
                    <td>{posao.opis}</td>
                    
                  </tr>
                ))}
              </tbody>
            </table>
          ) : (
            <p>Nema poslova koji odgovaraju vašem pretraživanju.</p>
          )}
        </div>
      </div>

      <Footer />
    </>
  );
};

