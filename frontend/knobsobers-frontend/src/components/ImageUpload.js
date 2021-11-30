import React, { useRef, useState } from 'react';
import { Card, Button, Form, Alert } from 'react-bootstrap';
import {useAuth} from '../contexts/AuthContext';
import {Link, useNavigate} from 'react-router-dom';

const ImageUpload = () => {

    const [file, setFile] = useState();    
    const [error, setError] = useState();
    const [loading, setLoading] = useState(false);
    const [message, setMessage] = useState();

    const types = ['image/png', 'image/jpeg', 'image/jpg'];
    const navigate = useNavigate();

    // const proifilePicRef = useRef(); 

    const handleFileUploadChange = e => {
        let selectedFile = e.target.files[0];

        if(selectedFile && types.includes(selectedFile.type)){
            setError('');
            setLoading(false);
            setFile(selectedFile);
            setMessage("That's a great choice!");
        }else{
            setFile(null);
            setLoading(true);
            setError('Select an Image only');
        }
    };

    const handleSubmit = e => {
        e.preventDefault();
        if(!error){
            navigate('/dashboard');
        }
    };

    return (
        <>
            <Card>
                <Card.Body>
                    <h2 className="text-center mb-4">Upload Profile Picture</h2>
                    {error ? <Alert variant="danger">{error}</Alert>
                           : message && <Alert variant="success">{message}</Alert>
                    }
                    <Form onSubmit={handleSubmit}>

                        <Form.Group id="text">
                            {/* <Form.Label>Upload Profile Picture</Form.Label> */}
                            <Form.Control type="file"  onChange={handleFileUploadChange} required/>
                        </Form.Group>                        
                        
                        <Button disabled={loading} className="w-100 text-center mt-4" type="submit">Upload</Button>
                    </Form>
                
                </Card.Body>
            </Card>
            <div className="w-100 text-center mt-2">
                Wanna do it later? <Link to="/dashboard">Skip</Link>
            </div>
        </>
    )
}

export default ImageUpload
