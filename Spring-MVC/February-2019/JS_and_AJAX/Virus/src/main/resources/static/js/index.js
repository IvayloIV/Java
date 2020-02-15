$(() => {
    const table = $('#table-content');
    const getDate = (url) => {
        return fetch(url, { method: 'GET' })
            .then(data => data.json());
    };

    $('input[name="showType"]').on('click', visualizeViruses);

    async function visualizeViruses(event) {
        const target = $(event.target);
        table.empty();

        if (target.attr('id') === 'type-viruses') {
            let data = await getDate('/api/virus/all');
            appendVirusTable(data);
        } else {
            let data = await getDate('/api/capital/all');
            appendCapitalsTable(data);
        }
    }

    function appendVirusTable(data) {
        table.append($('<h2>').text('All Viruses'))
             .append($('<table>').addClass('table')
                 .append($('<thead>')
                     .append($('<tr>')
                         .append($('<th>').attr('scope', 'col').text('#'))
                         .append($('<th>').attr('scope', 'col').text('Name'))
                         .append($('<th>').attr('scope', 'col').text('Magnitude'))
                         .append($('<th>').attr('scope', 'col').text('Released On'))
                         .append($('<th>').attr('scope', 'col'))
                         .append($('<th>').attr('scope', 'col'))))
                 .append($('<tbody>')));

        data.forEach((d, i) => table.find('tbody')
                            .append($('<tr>')
                                .append($('<th>').attr('scope', 'row').text(i))
                                .append($('<td>').text(d.name))
                                .append($('<td>').text(d.magnitude))
                                .append($('<td>').text(new Date(d.releasedOn).toLocaleDateString('en-US')))
                                .append($('<td>')
                                    .append($('<a>').attr('href', `/virus/edit/${d.id}`)
                                        .append($('<button>').attr('type', 'button').addClass('btn btn-light').text('Edit'))))
                                .append($('<td>')
                                    .append($('<a>').attr('href', `/virus/delete/${d.id}`)
                                        .append($('<button>').attr('type', 'button').addClass('btn btn-light').text('Delete')))))
                    );
    }

    function appendCapitalsTable(data) {
        table.append($('<h2>').text('All Capitals'))
            .append($('<table>').addClass('table')
                .append($('<thead>')
                    .append($('<tr>')
                        .append($('<th>').attr('scope', 'col').text('#'))
                        .append($('<th>').attr('scope', 'col').text('Name'))
                        .append($('<th>').attr('scope', 'col').text('Latitude'))
                        .append($('<th>').attr('scope', 'col').text('Longitude'))))
                .append($('<tbody>')));

        data.forEach((d, i) => table.find('tbody')
            .append($('<tr>')
                .append($('<th>').attr('scope', 'row').text(i))
                .append($('<td>').text(d.name))
                .append($('<td>').text(d.latitude))
                .append($('<td>').text(d.longitude))));
    }
});