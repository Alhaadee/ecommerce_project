import React from 'react'
import { Link } from 'react-router-dom'

const NotFound = () => {
  return (
    <>
    <h1>Error 404, page not found</h1>
    <h2><Link to="/">Back To Home Page</Link></h2>
    </>
  )
}

export default NotFound