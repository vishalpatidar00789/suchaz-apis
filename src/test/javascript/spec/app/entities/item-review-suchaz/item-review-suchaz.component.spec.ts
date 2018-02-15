/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { ItemReviewSuchazComponent } from '../../../../../../main/webapp/app/entities/item-review-suchaz/item-review-suchaz.component';
import { ItemReviewSuchazService } from '../../../../../../main/webapp/app/entities/item-review-suchaz/item-review-suchaz.service';
import { ItemReviewSuchaz } from '../../../../../../main/webapp/app/entities/item-review-suchaz/item-review-suchaz.model';

describe('Component Tests', () => {

    describe('ItemReviewSuchaz Management Component', () => {
        let comp: ItemReviewSuchazComponent;
        let fixture: ComponentFixture<ItemReviewSuchazComponent>;
        let service: ItemReviewSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [ItemReviewSuchazComponent],
                providers: [
                    ItemReviewSuchazService
                ]
            })
            .overrideTemplate(ItemReviewSuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ItemReviewSuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItemReviewSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new ItemReviewSuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.itemReviews[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
