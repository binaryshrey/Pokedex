/**
 * @type {import('gatsby').GatsbyConfig}
 */
module.exports = {
  plugins: [{
      resolve: `gatsby-plugin-manifest`,
      options: {
        name: 'Pokedex',
        short_name: 'Pokedex',
        start_url: '/',
        background_color: '#000000',
        theme_color: '#ffffff',
        display: 'standalone',
        icon: './logo.svg',
      },
    },
    {
      resolve: 'gatsby-plugin-html-attributes',
      options: {
        lang: 'en'
      }
    },
    {
      resolve: 'gatsby-plugin-netlify',
    }
  ],
  siteMetadata: {
    title: "Pokedex",
    description: "Pokedex • Your Ultimate Pokemon Companion App",
    image: `/logo.png`,
    siteUrl: `https://pokedex-zenstudio.netlify.app/`,
  },
}
