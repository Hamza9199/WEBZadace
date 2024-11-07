import React from 'react';  

import './Header.css';
import { Container, Nav, Navbar } from 'react-bootstrap';
import { Link } from 'react-router-dom';


const Header = () => {
    return (
        <Navbar bg="dark" variant="dark" fixed="top">
            <Container>
                <Navbar.Brand href="/">Sistem prijave za poso</Navbar.Brand>
                <Nav className="ml-auto">
                    <Nav.Link as={Link} to="/" className='nav-link'>Korisnik</Nav.Link>
                    <Nav.Link as={Link} to="/korisnik" className='nav-link'>Post Korisnik</Nav.Link>
                </Nav>
            </Container>
        </Navbar>
    );
}

export { Header };
