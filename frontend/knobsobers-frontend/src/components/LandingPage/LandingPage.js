import React from 'react';
import {Button} from 'react-bootstrap';
import {Link} from 'react-router-dom';

const LandingPage = () => {
    return (
        <>
        <h1>Knobsobers</h1>
        <Link to="/login">
            <Button className="text-center">
                Get Started
            </Button>
        </Link>
        </>
    )
}

export default LandingPage
