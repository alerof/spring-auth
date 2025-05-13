document.getElementById('login-form').addEventListener('submit', async function (event) {
  event.preventDefault(); // Prevent form submission from reloading the page

  // Get form input values
  const email = document.getElementById('email').value;
  const password = document.getElementById('password').value;

  // Prepare the request payload
  const payload = {
    email: email,
    password: password
  };

  // Define the authentication endpoint
  const endpoint = 'http://localhost:8080/auth/login';

  try {
    // Make the POST request to the backend
    let response = await fetch(endpoint, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(payload)
    });

    // Handle the response
    if (response.ok) {
      document.getElementById('success-message').textContent = 'Login successful!';
      document.getElementById('error-message').textContent = ''; // Clear any previous error message

      console.log('Login successful !!!'); // Log the successful response
    } else {
      // Handle errors (e.g., invalid credentials)
      document.getElementById('error-message').textContent = error.message || 'Login failed';
      document.getElementById('success-message').textContent = ''; // Clear any previous success message

      console.error('Error during login !!!');
    }
  } catch (error) {
    // Handle network or other unexpected errors
    document.getElementById('error-message').textContent = 'An unexpected error occurred. Please try again.';
    document.getElementById('success-message').textContent = '';
    console.error('Network error or other failure:', error);
  }
});