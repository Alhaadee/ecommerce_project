import React from 'react'
import { Link } from 'react-router-dom'

const NavBar = () => {
  return (
    <>
    <nav>
        <ul>
            <li><Link to="/">Login</Link></li>
            <li><Link to="/">Categories</Link></li>
            <li><Link to="/">Shopping Basket</Link></li>
        </ul>
    </nav>
    </>
  )
}

export default NavBar