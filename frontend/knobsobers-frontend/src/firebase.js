import firebase from 'firebase/compat/app';
import "firebase/compat/auth";
import 'firebase/compat/storage';
import 'firebase/compat/firestore';

const app = firebase.initializeApp({ 
  apiKey: 'AIzaSyA4uUv28eAORvwh-NjUZl7VkFMw8KPfSj8',
  authDomain: process.env.REACT_APP_FIREBASE_AUTH_DOMAIN,
  projectId: process.env.REACT_APP_FIREBASE_PROJECT_ID,
  storageBucket: process.env.REACT_APP_FIREBASE_STORAGE_BUCKET,
  messagingSenderId: process.env.REACT_APP_FIREBASE_MESSAGING_SENDER_ID,
  appId: process.env.REACT_APP_FIREBASE_API_ID
});

export const auth = app.auth();
export const projectStorage = firebase.storage();
export const projectFirestore = firebase.firestore();

export default app;