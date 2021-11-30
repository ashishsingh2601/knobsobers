import React, {useRef, useState} from 'react';
import { Card, Button, Form, Alert } from 'react-bootstrap';
import {useAuth} from '../contexts/AuthContext';
import {Link, useNavigate} from 'react-router-dom';

const Signup = () => {

    const emailRef = useRef();
    const passwordRef = useRef();
    const usernameRef = useRef(); 
    const passwordConfirmRef = useRef();
    const {signup}  = useAuth();
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const handleSubmit = async (e) =>{
        e.preventDefault();
        if(passwordRef.current.value !== passwordConfirmRef.current.value){
            return setError('You entered two different passwords');
        }
        
        try{
            setError('');
            setLoading(true);
            await signup(emailRef.current.value, passwordRef.current.value);
            navigate("/image-upload");
        }catch{
            setError('Failed to Register');
        };
        setLoading(false);          
    };

    return (
        <>
            <Card className="card">
                <Card.Body>
                    <h2 className="text-center mb-4">Register</h2>
                    {error && <Alert variant="danger">{error}</Alert>}
                    <Form onSubmit={handleSubmit}>

                        <Form.Group id="text">
                            <Form.Label>Username</Form.Label>
                            <Form.Control type="text" ref={usernameRef} required/>
                        </Form.Group>                        
                        <Form.Group id="email">
                            <Form.Label>E-mail</Form.Label>
                            <Form.Control type="email" ref={emailRef} required/>
                        </Form.Group>
                        <Form.Group id="password">
                            <Form.Label>Password</Form.Label>
                            <Form.Control type="password" ref={passwordRef} required/>
                        </Form.Group>
                        <Form.Group id="password-confirm">
                            <Form.Label>Password Confirmation</Form.Label>
                            <Form.Control type="password" ref={passwordConfirmRef} required/>
                        </Form.Group>
                        <Button disabled={loading} className="w-100 text-center mt-4" type="submit">Sign Up</Button>
                    </Form>
                
                </Card.Body>
            </Card>
            <div className="w-100 text-center mt-2">
                Already connected with us? <Link to="/login">Log In</Link>
            </div>
        </>
    )
}

export default Signup
