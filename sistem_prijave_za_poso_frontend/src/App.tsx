import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import './App.css';
import { Home } from './pages/home/Home';
import { Dashbord } from './pages/dashbord/Dashbord';
import NoMatch from './pages/noMatch/NoMatch';
import Login from './pages/login/Login';
import ProtectedRoute from './components/ProtectedRoute';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/" element={<ProtectedRoute element={<Home />} />} />
        <Route path="/dashboard" element={<ProtectedRoute element={<Dashbord />} />} />
        <Route path="/*" element={<NoMatch />} />
      </Routes>
    </Router>
  );
}

export default App;
