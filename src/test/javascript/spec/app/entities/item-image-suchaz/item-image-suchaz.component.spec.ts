/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { ItemImageSuchazComponent } from '../../../../../../main/webapp/app/entities/item-image-suchaz/item-image-suchaz.component';
import { ItemImageSuchazService } from '../../../../../../main/webapp/app/entities/item-image-suchaz/item-image-suchaz.service';
import { ItemImageSuchaz } from '../../../../../../main/webapp/app/entities/item-image-suchaz/item-image-suchaz.model';

describe('Component Tests', () => {

    describe('ItemImageSuchaz Management Component', () => {
        let comp: ItemImageSuchazComponent;
        let fixture: ComponentFixture<ItemImageSuchazComponent>;
        let service: ItemImageSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [ItemImageSuchazComponent],
                providers: [
                    ItemImageSuchazService
                ]
            })
            .overrideTemplate(ItemImageSuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ItemImageSuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItemImageSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new ItemImageSuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.itemImages[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
