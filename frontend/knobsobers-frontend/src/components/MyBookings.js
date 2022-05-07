import React, {useState} from 'react';
import {Button, Drawer, Modal, Space, notification} from 'antd';
import { ExclamationCircleOutlined } from '@ant-design/icons';

const { confirm } = Modal;

const MyBookings = (props) => {

    
    const [visible, setVisible] = useState(false);
        const showDrawer = () => {
            setVisible(true);
        };
        const onClose = () => {
            setVisible(false);
    };

    const showDeleteConfirm = () => {
        confirm({
            title: 'Are you sure about deleting your slot?',
            icon: <ExclamationCircleOutlined />,
            okText: 'Yes',
            okType: 'danger',
            cancelText: 'No',
            onOk() {
                handleCancel();
            },
            onCancel() {
            console.log('Cancel');
            },
  });
}

    const openNotificationWithIcon = (type, placement) => {
        notification[type]({
            message: 'Slot Deleted!',
            placement,
            duration: 3,
        });
    };

    const handleCancel = () => {
        openNotificationWithIcon('info', 'bottomLeft');
        props.setPickedValue("");
        props.setIsBooked(false);
    };

    return (
        <>
            <Button type="secondary" onClick={showDrawer} style={{ boxShadow: '5px 5px 5px rgba(29, 29, 29, 0.733)'}}>My Bookings</Button>
            <Drawer title="My Bookings" placement="right" onClose={onClose} visible={visible}>
                <h2>Date and Time</h2>
                <h4>{props.pickedValue}</h4>
                {
                    !props.isBooked ? <h6>No Bookings Yet</h6> :
                    props.isBooked && <Button type="danger" onClick={showDeleteConfirm}>Cancel Booking</Button>
                }
                
            </Drawer>
        </>
    )
}

export default MyBookings
