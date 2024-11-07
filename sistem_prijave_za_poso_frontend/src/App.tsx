import { Route, BrowserRouter as Router, Routes } from 'react-router-dom'
import './App.css'
import { Home } from './pages/home/Home'
import { Dashbord } from './pages/dashbord/Dashbord'
import NoMatch from './pages/noMatch/NoMatch'
import Login from './pages/login/Login'


function App() {

  return (
    <Router>

      <Routes>

        <Route path="/" element={<Home />} />

        <Route path="/*" element={<NoMatch/>} />

        <Route path="/dashboard" element={<Dashbord />} />

        <Route path="/login" element={<Login/>} />


      </Routes>
      
    </Router>
  )
}

export default App
