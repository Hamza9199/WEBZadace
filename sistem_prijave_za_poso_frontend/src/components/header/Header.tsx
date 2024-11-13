import React from 'react';  

import './Header.css';
import { Container, Nav, Navbar } from 'react-bootstrap';
import { Link } from 'react-router-dom';


const handleLogout = () => {
    localStorage.removeItem('isAuthenticated');
  };

const Header = () => {
    return (
        <Navbar bg="dark" variant="dark" fixed="top">
            <Container>
                <Navbar.Brand href="/">Sistem prijave za poso</Navbar.Brand>
                <Nav className="ml-auto">
                    <Nav.Link as={Link} to="/" className='nav-link'>Korisnik</Nav.Link>
                    <Nav.Link as={Link} to="/login" onClick={handleLogout} className='nav-link'>Logout</Nav.Link>
                    <Nav.Link as={Link} to="/dashboard" className='nav-link'>Lista Korisnika</Nav.Link>
                </Nav>
            </Container>
        </Navbar>
    );
}

export { Header };
