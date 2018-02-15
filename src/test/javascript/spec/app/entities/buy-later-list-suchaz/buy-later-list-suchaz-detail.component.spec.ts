/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { BuyLaterListSuchazDetailComponent } from '../../../../../../main/webapp/app/entities/buy-later-list-suchaz/buy-later-list-suchaz-detail.component';
import { BuyLaterListSuchazService } from '../../../../../../main/webapp/app/entities/buy-later-list-suchaz/buy-later-list-suchaz.service';
import { BuyLaterListSuchaz } from '../../../../../../main/webapp/app/entities/buy-later-list-suchaz/buy-later-list-suchaz.model';

describe('Component Tests', () => {

    describe('BuyLaterListSuchaz Management Detail Component', () => {
        let comp: BuyLaterListSuchazDetailComponent;
        let fixture: ComponentFixture<BuyLaterListSuchazDetailComponent>;
        let service: BuyLaterListSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [BuyLaterListSuchazDetailComponent],
                providers: [
                    BuyLaterListSuchazService
                ]
            })
            .overrideTemplate(BuyLaterListSuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BuyLaterListSuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BuyLaterListSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new BuyLaterListSuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.buyLaterList).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
