import React, {useRef} from 'react';
import { Card, Button, Form } from 'react-bootstrap';

const Signup = () => {

    const emailRef = useRef();
    const passwordRef = useRef();
    const passwordConfirmRef = useRef();

    return (
        <>
            <Card>
                <Card.Body>
                    <h2 className="text-center mb-4">Register</h2>
                    <Form>
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
                        <Button className="w-100 text-center mt-4" type="submit">Sign Up</Button>
                    </Form>
                
                </Card.Body>
            </Card>
            <div className="w-100 text-center mt-2">
                Already connected with us? Log In
            </div>
        </>
    )
}

export default Signup
