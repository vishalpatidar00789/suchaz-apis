/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { GiftWrapperSuchazDetailComponent } from '../../../../../../main/webapp/app/entities/gift-wrapper-suchaz/gift-wrapper-suchaz-detail.component';
import { GiftWrapperSuchazService } from '../../../../../../main/webapp/app/entities/gift-wrapper-suchaz/gift-wrapper-suchaz.service';
import { GiftWrapperSuchaz } from '../../../../../../main/webapp/app/entities/gift-wrapper-suchaz/gift-wrapper-suchaz.model';

describe('Component Tests', () => {

    describe('GiftWrapperSuchaz Management Detail Component', () => {
        let comp: GiftWrapperSuchazDetailComponent;
        let fixture: ComponentFixture<GiftWrapperSuchazDetailComponent>;
        let service: GiftWrapperSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [GiftWrapperSuchazDetailComponent],
                providers: [
                    GiftWrapperSuchazService
                ]
            })
            .overrideTemplate(GiftWrapperSuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(GiftWrapperSuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GiftWrapperSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new GiftWrapperSuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.giftWrapper).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
