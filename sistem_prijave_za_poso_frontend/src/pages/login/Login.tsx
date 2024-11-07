import { useState } from 'react';
import './Login.css';
import { Form, Button, FormControl, FormGroup } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

const Login = () => {
    const [formData, setFormData] = useState({
        email: '',
        password: ''
    });

    const navigate = useNavigate();

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
    
    
        const response = await fetch('http://localhost:8080/api/korisnik', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData),
        });
        
        if (!response.ok) {
            console.error('Neuspešna prijava');
            return;
        }
        

            const data = await response.json();
            console.log(data);  
            navigate('/');
        
        
    };
    
    
    

   

    return (
        <div className="center-form">
            <h1 className="naslov">Login</h1>
            <Form onSubmit={handleSubmit}>
                <FormGroup controlId="formBasicEmail">
                    <FormControl
                        type="text"
                        name="email"
                        placeholder="Enter email"
                        value={formData.email}
                        onChange={handleInputChange}
                    />
                </FormGroup>

                <FormGroup controlId="formBasicPassword">
                    <FormControl
                        type="password"
                        name="password"
                        placeholder="Enter password"
                        value={formData.password}
                        onChange={handleInputChange}
                    />
                </FormGroup>

                <Button variant="primary" type="submit" className="submit-button">
                    Login
                </Button>
            </Form>
        </div>
    );
};

export default Login;
