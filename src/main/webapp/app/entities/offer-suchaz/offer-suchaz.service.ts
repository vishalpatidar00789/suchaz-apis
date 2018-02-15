import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { OfferSuchaz } from './offer-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<OfferSuchaz>;

@Injectable()
export class OfferSuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/offers';

    constructor(private http: HttpClient) { }

    create(offer: OfferSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(offer);
        return this.http.post<OfferSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(offer: OfferSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(offer);
        return this.http.put<OfferSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<OfferSuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<OfferSuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<OfferSuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OfferSuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: OfferSuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<OfferSuchaz[]>): HttpResponse<OfferSuchaz[]> {
        const jsonResponse: OfferSuchaz[] = res.body;
        const body: OfferSuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to OfferSuchaz.
     */
    private convertItemFromServer(offer: OfferSuchaz): OfferSuchaz {
        const copy: OfferSuchaz = Object.assign({}, offer);
        return copy;
    }

    /**
     * Convert a OfferSuchaz to a JSON which can be sent to the server.
     */
    private convert(offer: OfferSuchaz): OfferSuchaz {
        const copy: OfferSuchaz = Object.assign({}, offer);
        return copy;
    }
}
