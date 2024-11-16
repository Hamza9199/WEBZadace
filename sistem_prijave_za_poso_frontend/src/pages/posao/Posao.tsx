import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

interface JobCategory {
  id: number;
  naziv: string; 
}

interface Job {
  id: number;
  naziv: string;
  opis: string;
  rating: number;
  kategorija: JobCategory; 
}

const Posao = () => {
  const { id } = useParams<{ id: string }>(); 
  const [job, setJob] = useState<Job | null>(null); 
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    if (id) {
      const fetchJobDetails = async () => {
        try {
          const response = await fetch(`http://localhost:8080/api/jobs/${id}`);
          if (!response.ok) {
            throw new Error("Nije moguće dohvatiti podatke o poslu.");
          }
          const data = await response.json();
          setJob(data); 
        } catch (error) {
            console.error("Došlo je do greške:", error);
        } finally {
          setLoading(false); 
        }
      };
      fetchJobDetails();
    }
  }, [id]); 

  if (loading) {
    return <div>Učitavanje...</div>;
  }


  return (
    <div className="job-details">
      {job ? (
        <div>
          <h2>{job.naziv}</h2>
          <p><strong>Kategorija:</strong> {job.kategorija.naziv}</p> 
          <p><strong>Ocjena:</strong> {job.rating}</p> 
          <p><strong>Opis:</strong></p>
          <p>{job.opis}</p>
        </div>
      ) : (
        <div>Posao nije pronađen.</div>
      )}
    </div>
  );
};

export default Posao;
