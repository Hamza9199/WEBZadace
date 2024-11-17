import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import './App.css';
import { Home } from './pages/home/Home';
import { Dashbord } from './pages/dashbord/Dashbord';
import NoMatch from './pages/noMatch/NoMatch';
import Login from './pages/login/Login';
import ProtectedRoute from './components/ProtectedRoute';
import NoviKorisnik from './pages/noviKorisnik/noviKorisnik';
import Register from './pages/register/Register';
import Posao from './pages/posao/Posao';
import Profil from './pages/profil/Profil';
import NoviPosao from './pages/noviPosao/NoviPosao';
import { NoviReview } from './pages/noviReview/NoviReview';
import { Review } from './pages/review/Review';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register/>} />
        <Route path="/" element={<ProtectedRoute element={<Home />} />} />
        <Route path="/dashboard" element={<ProtectedRoute element={<Dashbord />} />} />
        <Route path="/dodajKorisnika" element={<ProtectedRoute element={< NoviKorisnik/>} />} />
        <Route path="/updateKorisnika/:id" element={<ProtectedRoute element={< NoviKorisnik/>} />} />
        <Route path="/dodajPosao" element={<ProtectedRoute element={< NoviPosao/>} />} />
        <Route path="/updatePosao/:id" element={<ProtectedRoute element={< NoviPosao/>} />} />
        <Route path="/posao/:id" element={<ProtectedRoute element={< Posao/>} />} />
        <Route path="/profil/:id" element={<ProtectedRoute element={< Profil/>} />} />
        <Route path="/updateReview/:id" element={<ProtectedRoute element={< NoviReview/>} />} />
        <Route path="/noviReview" element={<ProtectedRoute element={< NoviReview/>} />} />
        <Route path="/review/:id" element={<ProtectedRoute element={< Review/>} />} />



        <Route path="/*" element={<NoMatch />} />
      </Routes>
    </Router>
  );
}

export default App;
