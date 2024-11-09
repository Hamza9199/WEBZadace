import axios from 'axios';

const REST_API_URL = "http://localhost:8080/api/korisnik/";
 

export const getKorisnici = async () => {
    return await axios.get(REST_API_URL);
}

interface Korisnik {
    email: string;
    password: string;
}

export const saveKorisnikS = async (data: Korisnik) => {
    return await axios.post(REST_API_URL, data);
}




