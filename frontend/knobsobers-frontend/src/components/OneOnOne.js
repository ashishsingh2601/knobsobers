import React, { useState, useEffect } from 'react'
import MainNavbar from './Navbar/MainNavbar'
import {Card} from 'react-bootstrap';
import AvailableUsers from '../data/availableUsers.json';
import {Modal, Button, Rate, notification, Drawer, Form, Input} from 'antd';
import { DatePicker, Space } from 'antd';
import MyBookings from './MyBookings';
import SearchBar from './SearchBar';
import { ExclamationCircleOutlined, MailOutlined } from '@ant-design/icons';
import emailjs from  'emailjs-com';


const { confirm } = Modal;

const OneonOne = () => {

    const [isModalVisible, setIsModalVisible] = useState(false);
    const { RangePicker } = DatePicker;
    const [pickedValue, setPickedValue] = useState("");
    const [isBooked, setIsBooked] = useState(false);
    const [fUsers, setFUsers] = useState([]);
    // const [allUsers, setAllUsers] = useState([]);
    const [hasFiltered, setHasFiltered] = useState(false);
    const [mailDrawerVisible, setMailDrawerVisible] = useState(false);
    
    // setUsers(users=>[...users, ...AvailableUsers]);
    let users = [];
    let allUsers = [];
    users = AvailableUsers;
    allUsers = AvailableUsers;

        const openSelectThenBook = () => {
        confirm({
            title: "Can't Book your slot!",
            icon: <ExclamationCircleOutlined />,
            content: 'You have not selected Date and Time, select to book your slot.',
            okText: 'Alright!',
            okType: 'danger',
            onOk() {
            console.log('OK');
            },
        });
    }        
        

        function onChange(value, dateString) {
            setPickedValue(dateString);
            // if(pickedValue.length === 0){
            //     openSelectThenBook();
            // }else{
                setIsBooked(true);
            // }
            console.log('Selected Time: ', value);
            console.log('Formatted Selected Time: ', dateString);
        }

        function onOk(value) {
            console.log('onOk: ', value);
        }



    const showModal = () => {
        setIsModalVisible(true);
    };

    const openNotificationWithIcon = (type, placement) => {
        notification[type]({
            message: 'Slot Booked Successfully!',
            placement,
            duration: 3,
        });
    };

    const openAlreadyBooked = () => {
        confirm({
            title: 'You have already booked a slot!',
            icon: <ExclamationCircleOutlined />,
            content: 'Delete that slot to book new',
            okText: 'Gotcha!',
            okType: 'danger',
            onOk() {
            console.log('OK');
            },
        });
    }        

    const handleOk = () => {
        if(pickedValue.length === 0){
            openSelectThenBook();
            setIsBooked(false);

        }else{

            setIsBooked(true);
            setIsModalVisible(false);
            {
                isBooked && openNotificationWithIcon('success', 'bottomRight')
                //  : isBooked && openAlreadyBooked()

                
            }
        }        
    };

    const handleCancel = () => {
        setIsModalVisible(false);
    };

    const filterUserCards = (event) => {
        event.preventDefault();
        const value = event.target.value.toLowerCase();
        console.log(value);
        setHasFiltered(true);
        console.log(" all ",allUsers);
        console.log(users);

        const filteredUsers = allUsers.filter(

            user => (`${user.domain}`
                    .toLowerCase()
                    .includes(value)
            )
        );
        users = filteredUsers;
        console.log("after",users);
        setFUsers(users);
    };

    const showMailDrawer = () => {
        setMailDrawerVisible(true);
    };

    const onMailDrawerClose = () => {
        setMailDrawerVisible(false);
    };

    const handleSendMail = (e) => {
        e.preventDefault();

        emailjs.sendForm('service_f5c4yim', 'template_fm8kdfd', e.target, 'Xg8z2WRp07Rn5JOJx')
        .then((result) => {
            console.log(result.text);
        }, (error) => {
            console.log(error.text);
        });
        e.target.reset();
    }

    return (
        <>
            <MainNavbar />
            <div className="oneonone-head">
                {/* <h3>Available Now</h3> */}
                <SearchBar filterUserCards={filterUserCards} />
                <MyBookings pickedValue={pickedValue} isBooked={isBooked} setPickedValue={setPickedValue} setIsBooked={setIsBooked}/>
            </div>
            
            <div className="available-users">
                {   !hasFiltered ? 
                    
                    users.map(user=>{
                    return(
                        <>
                            <div className="one-on-one-cards">
                                <Card className="text-center mb-3 user-card">
                                    <Card.Header><span style={{color: '#00bf00'}}>Active Now</span></Card.Header>
                                    <Card.Body>
                                        <Card.Title>{user.name}</Card.Title>
                                        <Card.Text>
                                            Profession: {user.domain}
                                        </Card.Text>
                                        <Card.Text>
                                            Work Experience: {user.experience}
                                        </Card.Text>
                                        <div style={{display: 'flex', justifyContent: 'space-between'}}>
                                            <Rate allowHalf disabled defaultValue={user.rating} />
                                            {
                                                isBooked ? (<Button type="primary" onClick={openAlreadyBooked}>Book Meeting</Button>)
                                                : !isBooked && <Button type="primary" onClick={showModal}>Book Meeting</Button>
                                            }
                                                                                        
                                        </div>
                                        <div>
                                            <Button type="primary" 
                                                onClick={showMailDrawer} 
                                                style={{
                                                    position: 'relative',
                                                    width:'100%', 
                                                    display:'flex',
                                                    marginTop:'1rem', 
                                                    textAlign:'center', 
                                                    justifyContent:'center',
                                                    backgroundColor: '#ffa500',
                                                    border: 'none'}}
                                            ><MailOutlined />Send Mail</Button>
                                             <Drawer title="Send Mail" placement="left" onClose={onMailDrawerClose}  width={540} visible={mailDrawerVisible}>
                                                {/* <Form >
                                                    <Form.Item  name="name"
                                                                rules={[{ required: true, message: 'Please enter your name' }]}
                                                    >
                                                        <Input placeholder="Enter your name"/>
                                                    </Form.Item>
                                                    <Form.Item
                                                        name="email"
                                                        
                                                        rules={[
                                                        {
                                                            type: 'email',
                                                            message: "Your input mail ain't valid!",
                                                        },
                                                        {
                                                            required: true,
                                                            message: 'Please enter your E-mail!',
                                                        },
                                                        ]}
                                                    >
                                                        <Input placeholder="Enter your E-mail"/>
                                                    </Form.Item>
                                                    <Form.Item name="subject"
                                                                rules={[{ required: true, message: 'Please enter mail subject' }]}        
                                                    >
                                                        <Input placeholder="Subject"/>
                                                    </Form.Item>
                                                    <Form.Item
                                                        name="message"
                                                        
                                                        rules={[{ required: true, message: 'Please input your message' }]}
                                                    >
                                                        <Input.TextArea showCount maxLength={100} placeholder="What's on your mind!?"/>
                                                    </Form.Item>
                                                    <Form.Item wrapperCol={{ offset: 8 }}>
                                                        <Button type="primary" htmlType="submit" onClick={handleSendMail}>
                                                            Send Mail
                                                        </Button>
                                                    </Form.Item>
                                                </Form> */}
                                                <form onSubmit={handleSendMail}>
                                                    <div className="row pt-5 mx-auto">
                                                        <div className="col-8 form-group mx-auto">
                                                            <input type="text" className="form-control" placeholder="Enter your name" name="name" required/>
                                                        </div>
                                                        <div className="col-8 form-group pt-2 mx-auto">
                                                            <input type="email" className="form-control" placeholder="Enter your mail" name="email" required/>
                                                        </div>
                                                        <div className="col-8 form-group pt-2 mx-auto">
                                                            <input type="text" className="form-control" placeholder="Subject" name="subject" required/>
                                                        </div>
                                                        <div className="col-8 form-group pt-2 mx-auto">
                                                            <textarea className="form-control" id="" cols="30" rows="8" placeholder="What's on your mind!?" name="message" required></textarea>
                                                        </div>
                                                        <div className="col-8 pt-3 mx-auto">
                                                            <input type="submit" className="btn btn-info" value="Send Message" style={{color: '#fff'}}></input>
                                                        </div>
                                                    </div>
                                                </form>
                                            </Drawer>
                                        </div>
                                        {/* <Rate allowHalf disabled defaultValue={user.rating} />
                                        <Button type="primary" onClick={showModal}>Book Meeting</Button> */}
                                    </Card.Body>
                                </Card>
                            </div>
                            <Modal title="Book Slot" visible={isModalVisible} onOk={handleOk} onCancel={handleCancel}>
                                <Space direction="vertical" size={12}>
                                    <DatePicker showTime onChange={onChange} onOk={onOk} />
                                </Space>
                            </Modal>
                        </>
                    );
                })                    

                    :
                    fUsers.map(user=>{
                    return(
                        <>
                            <div className="one-on-one-cards">
                                <Card className="text-center mb-3 user-card">
                                    <Card.Header><span style={{color: '#00ff00'}}>Active Now</span></Card.Header>
                                    <Card.Body>
                                        <Card.Title>{user.name}</Card.Title>
                                        <Card.Text>
                                            Profession: {user.domain}
                                        </Card.Text>
                                        <Card.Text>
                                            Work Experience: {user.experience}
                                        </Card.Text>
                                        <div style={{display: 'flex', justifyContent: 'space-between'}}>
                                            <Rate allowHalf disabled defaultValue={user.rating} />
                                            {
                                                isBooked ? (<Button type="primary" onClick={openAlreadyBooked}>Book Meeting</Button>)
                                                : !isBooked && <Button type="primary" onClick={showModal}>Book Meeting</Button>
                                            }                                           
                                        </div>
                                        {/* <Button type="primary" onClick={showModal}>Book Meeting</Button> */}
                                    </Card.Body>
                                </Card>
                            </div>
                            <Modal title="Book Slot" visible={isModalVisible} onOk={handleOk} onCancel={handleCancel}>
                                <Space direction="vertical" size={12}>
                                    <DatePicker showTime onChange={onChange} onOk={onOk} />
                                </Space>
                            </Modal>
                        </>
                    );
                })
            }
            </div>
        </>
    )
}

export default OneonOne
