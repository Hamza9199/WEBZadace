import React, { useEffect, useState } from 'react';  

import './Header.css';
import { Container, Nav, Navbar } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';




const Header = () => {
    const [isAdmin, setIsAdmin] = useState(false);
    const navigate = useNavigate();

    const handleLogout = async () => {
    
        const response = await fetch('http://localhost:8080/api/logout', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        console.log(response.status)

        localStorage.removeItem('isAuthenticated');
        localStorage.removeItem('userId'); 
    
        navigate('/login');
      };

    const userId = localStorage.getItem('userId');

    useEffect(() => {
        const fetchIsAdmin = async () => {
            try {
                const response = await fetch(`http://localhost:8080/api/korisnik/${userId}/is-admin`);
                if (response.ok) {
                    const data = await response.json();
                    setIsAdmin(data);
                } else {
                    console.error('Failed to fetch admin status');
                }
            } catch (error) {
                console.error('Error:', error);
            }
        };

        if (userId) {
            fetchIsAdmin();
        }
    }, [userId]);

    return (
        <Navbar bg="dark" variant="dark" fixed="top">
            <Container>
                <Navbar.Brand href="/">Sistem prijave za poso</Navbar.Brand>
                <Nav className="ml-auto">
                {isAdmin && (
                        <Nav.Link as={Link} to="/dashboard" className="nav-link">
                            Dashboard
                        </Nav.Link>
                    )}
                    <Nav.Link as={Link} to={`/profil/${userId}`} className="nav-link">Profil</Nav.Link> 
                    <Nav.Link as={Link} to="/login" onClick={handleLogout} className='nav-link'>Logout</Nav.Link>
                    </Nav>
            </Container>
        </Navbar>
    );
}

export { Header };
