import React, {useState} from 'react';
import {Button, Card, Alert} from 'react-bootstrap';
import {useAuth} from "../../contexts/AuthContext";
import {Link, useNavigate} from 'react-router-dom';

const Dashboard = () => {
    
    const [error, setError] = useState('');
    const { logout } = useAuth();
    const navigate = useNavigate();

    const handleLogout = async () => {
        setError('');
        try{
            await logout();
            navigate("/login");
        }catch{
            setError('Failed to Log-out')
        }
    }

    return (
        <>      
            {error && <Alert variant="danger">{error}</Alert>}
            <h1>
                It's your own personalized dashboard
            </h1>
            
            <Button className="text-center" onClick={handleLogout}>
                Logout
            </Button>
            
        </>
    );
}

export default Dashboard
