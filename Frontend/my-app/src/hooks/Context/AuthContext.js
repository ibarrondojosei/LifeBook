import { createContext, useContext, useState} from "react";




export const authContext = createContext();

export function AuthProvider({children}){

    const [user, setuser] = useState(null);
    
  
  
    const login = (user) => {
      setuser(user)
    }
    
      
    
      
    
      
  
    const logout = () => {
      setuser(null)
    }

      
    
      
    
  
  
    return(
      <authContext.Provider value={{user, login, logout}}>
        {children}
      </authContext.Provider>
    )
}

export function useAuth(){
    return useContext(authContext)
}