/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { BuyLaterListSuchazComponent } from '../../../../../../main/webapp/app/entities/buy-later-list-suchaz/buy-later-list-suchaz.component';
import { BuyLaterListSuchazService } from '../../../../../../main/webapp/app/entities/buy-later-list-suchaz/buy-later-list-suchaz.service';
import { BuyLaterListSuchaz } from '../../../../../../main/webapp/app/entities/buy-later-list-suchaz/buy-later-list-suchaz.model';

describe('Component Tests', () => {

    describe('BuyLaterListSuchaz Management Component', () => {
        let comp: BuyLaterListSuchazComponent;
        let fixture: ComponentFixture<BuyLaterListSuchazComponent>;
        let service: BuyLaterListSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [BuyLaterListSuchazComponent],
                providers: [
                    BuyLaterListSuchazService
                ]
            })
            .overrideTemplate(BuyLaterListSuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BuyLaterListSuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BuyLaterListSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new BuyLaterListSuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.buyLaterLists[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
