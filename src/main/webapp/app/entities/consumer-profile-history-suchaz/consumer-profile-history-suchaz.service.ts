import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { ConsumerProfileHistorySuchaz } from './consumer-profile-history-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<ConsumerProfileHistorySuchaz>;

@Injectable()
export class ConsumerProfileHistorySuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/consumer-profile-histories';

    constructor(private http: HttpClient) { }

    create(consumerProfileHistory: ConsumerProfileHistorySuchaz): Observable<EntityResponseType> {
        const copy = this.convert(consumerProfileHistory);
        return this.http.post<ConsumerProfileHistorySuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(consumerProfileHistory: ConsumerProfileHistorySuchaz): Observable<EntityResponseType> {
        const copy = this.convert(consumerProfileHistory);
        return this.http.put<ConsumerProfileHistorySuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ConsumerProfileHistorySuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<ConsumerProfileHistorySuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<ConsumerProfileHistorySuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<ConsumerProfileHistorySuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: ConsumerProfileHistorySuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<ConsumerProfileHistorySuchaz[]>): HttpResponse<ConsumerProfileHistorySuchaz[]> {
        const jsonResponse: ConsumerProfileHistorySuchaz[] = res.body;
        const body: ConsumerProfileHistorySuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to ConsumerProfileHistorySuchaz.
     */
    private convertItemFromServer(consumerProfileHistory: ConsumerProfileHistorySuchaz): ConsumerProfileHistorySuchaz {
        const copy: ConsumerProfileHistorySuchaz = Object.assign({}, consumerProfileHistory);
        return copy;
    }

    /**
     * Convert a ConsumerProfileHistorySuchaz to a JSON which can be sent to the server.
     */
    private convert(consumerProfileHistory: ConsumerProfileHistorySuchaz): ConsumerProfileHistorySuchaz {
        const copy: ConsumerProfileHistorySuchaz = Object.assign({}, consumerProfileHistory);
        return copy;
    }
}
