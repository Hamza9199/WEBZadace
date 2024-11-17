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



    const [errors, setErrors] = useState({
        email: "",
        password: ""
    });

    

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
    
        if (validate()) {
            try {
                const response = await fetch('http://localhost:8080/api/login', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(formData),
                });
    
                if (!response.ok) {
                    const error = await response.text(); 
                    alert(error);
                    return;
                }
    
                const data = await response.json();
                console.log(data);
                localStorage.setItem('isAuthenticated', 'true');
                localStorage.setItem('userId', data.id); 
                navigate('/');
            } catch (error) {
                console.error('Greška prilikom logovanja:', error);
            }
        }
    };
    

    const validate = () => {
        let valid = true;
        const errorsCopy = { ...errors };

        if (formData.email === "") {
            errorsCopy.email = "Email je obavezan";
            valid = false;
        } else if (!formData.email.includes("@")) {
            errorsCopy.email = "Email mora sadrzati @";
            valid = false;
        } else if (!formData.email.includes(".")) {
            errorsCopy.email = "Email mora sadrzati tacku";
            valid = false;
        } else if (formData.email.length < 5) {
            errorsCopy.email = "Email mora sadrzati vise od 5 karaktera";
            valid = false;
        } else if (formData.email.length > 50) {
            errorsCopy.email = "Email mora sadrzati manje od 50 karaktera";
            valid = false;
        } else if (formData.email.includes(" ")) {
            errorsCopy.email = "Email ne sme sadrzati razmak";
            valid = false;
        } else if (formData.email.includes("admin")) {
            errorsCopy.email = "Email ne sme sadrzati admin";
            valid = false;
        } else {
            errorsCopy.email = "";
        }

        if (formData.password === "") {
            errorsCopy.password = "Sifra je obavezna";
            valid = false;
        } else if (formData.password.length < 5) {
            errorsCopy.password = "Sifra mora sadrzati vise od 5 karaktera";
            valid = false;
        } else if (formData.password.length > 50) {
            errorsCopy.password = "Sifra mora sadrzati manje od 50 karaktera";
            valid = false;
        } else if (formData.password.includes(" ")) {
            errorsCopy.password = "Sifra ne sme sadrzati razmak";
            valid = false;
        } else if (formData.password.includes("admin")) {
            errorsCopy.password = "Sifra ne sme sadrzati admin";
            valid = false;
        } else {
            errorsCopy.password = "";
        }

        setErrors(errorsCopy);
        return valid;
    };

    const handleChange = () => {
        navigate("/register")
    }
   

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
                isInvalid={!!errors.email}
                />
                <FormControl.Feedback type="invalid">
                {errors.email}
                </FormControl.Feedback>
            </FormGroup>

            <FormGroup controlId="formBasicPassword">
                <FormControl
                type="password"
                name="password"
                placeholder="Enter password"
                value={formData.password}
                onChange={handleInputChange}
                isInvalid={!!errors.password}
                />
                <FormControl.Feedback type="invalid">
                {errors.password}
                </FormControl.Feedback>
            </FormGroup>

            <Button variant="primary" type="submit" className="submit-button">
                Login
            </Button>
            </Form>


            <label onClick={handleChange}>
                Nemate profil?
            </label>
        </div>
    );
};

export default Login;
