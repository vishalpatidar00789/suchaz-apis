/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { ItemReviewSuchazDetailComponent } from '../../../../../../main/webapp/app/entities/item-review-suchaz/item-review-suchaz-detail.component';
import { ItemReviewSuchazService } from '../../../../../../main/webapp/app/entities/item-review-suchaz/item-review-suchaz.service';
import { ItemReviewSuchaz } from '../../../../../../main/webapp/app/entities/item-review-suchaz/item-review-suchaz.model';

describe('Component Tests', () => {

    describe('ItemReviewSuchaz Management Detail Component', () => {
        let comp: ItemReviewSuchazDetailComponent;
        let fixture: ComponentFixture<ItemReviewSuchazDetailComponent>;
        let service: ItemReviewSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [ItemReviewSuchazDetailComponent],
                providers: [
                    ItemReviewSuchazService
                ]
            })
            .overrideTemplate(ItemReviewSuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ItemReviewSuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItemReviewSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new ItemReviewSuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.itemReview).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
