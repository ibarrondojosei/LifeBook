import React from 'react'
import {initializeApp} from 'firebase/app';
import {getAuth} from 'firebase/auth';

const firebaseConfig = {
    apiKey: "AIzaSyCzLBSUpRqPQFwSdsR9NS_VoOlajZYFYwI",
    authDomain: "nc-s5-a612f.firebaseapp.com",
    projectId: "nc-s5-a612f",
    storageBucket: "nc-s5-a612f.appspot.com",
    messagingSenderId: "152294695474",
    appId: "1:152294695474:web:a58d7d755b02fa0f23122b"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
export const auth = getAuth(app)