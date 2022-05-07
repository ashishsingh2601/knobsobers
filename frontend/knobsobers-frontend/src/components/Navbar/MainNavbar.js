import React, {useState} from 'react';
import './MainNavbar.css';
import {Container, Nav, NavDropdown, Navbar} from 'react-bootstrap';
import {Button, Card, Alert} from 'react-bootstrap';
import {useAuth} from "../../contexts/AuthContext";
import {Link, useNavigate} from 'react-router-dom';


const MainNavbar = () => {

    const [error, setError] = useState('');
    const { logout, currentUser } = useAuth();
    const navigate = useNavigate();

    const handleLogout = async () => {
        setError('');
        try{
            await logout();
            navigate("/login");
        }catch{
            setError('Failed to Log-out');
        }
    }

    // const handleSelect = ()=>{
    //     style={
    //         borderBottom: "1px solid black"  
    //     };
    // };

    const style= (options) =>{
            return {
                borderBottom: "1px solid black !important"  
            }
        };

    return (
        <>
        <Navbar collapseOnSelect expand="lg" bg="light" variant="light" className="dashboard-navbar">
            <Container className="mainnav-container">
                <Navbar.Brand href="#home"><h1>Knobsobers</h1></Navbar.Brand>
                {/* <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                <Navbar.Collapse id="responsive-navbar-nav"> */}
                
                        <Nav className="ml-auto " defaultActiveKey="/dashboard/experiences">
                            <Nav.Link><Link to="/dashboard/experiences" className="navbar-link text-muted">Experiences</Link></Nav.Link>
                            <Nav.Link eventKey="2" ><Link to="/dashboard/one-on-one" className="navbar-link text-muted">One-on-One</Link></Nav.Link>
                            <Nav.Link eventKey="3" ><Link to="/dashboard/resources" className="navbar-link text-muted">Resources</Link></Nav.Link>
                            {/* <Nav.Link href="#pricing" style={{borderBottom: '1px solid black'}}>Experiences</Nav.Link> */}
                            <Nav.Link onClick={handleLogout} className="btn btn-secondary" style={{color: '#fff', marginLeft: '3rem', boxShadow: '5px 5px 5px rgba(29, 29, 29, 0.733)' }}>Logout</Nav.Link>
                        </Nav>
                {/* </Navbar.Collapse> */}
                
            </Container>
        
        </Navbar>
        <hr />
        </>
    )
}

export default MainNavbar
