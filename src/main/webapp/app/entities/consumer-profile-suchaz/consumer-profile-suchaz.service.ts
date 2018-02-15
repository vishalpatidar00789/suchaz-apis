import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { ConsumerProfileSuchaz } from './consumer-profile-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<ConsumerProfileSuchaz>;

@Injectable()
export class ConsumerProfileSuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/consumer-profiles';

    constructor(private http: HttpClient) { }

    create(consumerProfile: ConsumerProfileSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(consumerProfile);
        return this.http.post<ConsumerProfileSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(consumerProfile: ConsumerProfileSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(consumerProfile);
        return this.http.put<ConsumerProfileSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ConsumerProfileSuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<ConsumerProfileSuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<ConsumerProfileSuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<ConsumerProfileSuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: ConsumerProfileSuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<ConsumerProfileSuchaz[]>): HttpResponse<ConsumerProfileSuchaz[]> {
        const jsonResponse: ConsumerProfileSuchaz[] = res.body;
        const body: ConsumerProfileSuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to ConsumerProfileSuchaz.
     */
    private convertItemFromServer(consumerProfile: ConsumerProfileSuchaz): ConsumerProfileSuchaz {
        const copy: ConsumerProfileSuchaz = Object.assign({}, consumerProfile);
        return copy;
    }

    /**
     * Convert a ConsumerProfileSuchaz to a JSON which can be sent to the server.
     */
    private convert(consumerProfile: ConsumerProfileSuchaz): ConsumerProfileSuchaz {
        const copy: ConsumerProfileSuchaz = Object.assign({}, consumerProfile);
        return copy;
    }
}
